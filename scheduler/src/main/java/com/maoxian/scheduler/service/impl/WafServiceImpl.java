package com.maoxian.scheduler.service.impl;

import com.github.dockerjava.api.model.CpuStatsConfig;
import com.github.dockerjava.api.model.MemoryStatsConfig;
import com.github.dockerjava.api.model.Statistics;
import com.github.dockerjava.core.InvocationBuilder;
import com.maoxian.scheduler.exception.BusinessException;
import com.maoxian.scheduler.mapper.ImageMapper;
import com.maoxian.scheduler.mapper.WafMapper;
import com.maoxian.scheduler.mapper.WafMonitorMapper;
import com.maoxian.scheduler.pojo.ImageInfo;
import com.maoxian.scheduler.pojo.Waf;
import com.maoxian.scheduler.pojo.WafMonitor;
import com.maoxian.scheduler.service.DockerService;
import com.maoxian.scheduler.service.WafService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Lin
 * @date 2023/12/18 23:35
 */
@Slf4j
@Service
public class WafServiceImpl implements WafService {

    @Autowired
    private WafMapper wafMapper;

    @Autowired
    private WafMonitorMapper wafMonitorMapper;

    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private DockerService dockerService;

    @Override
    public void online(Long id) {
        // 查询waf
        Waf waf = wafMapper.selectById(id);
        if (waf == null) {
            throw new BusinessException("waf不存在");
        }

        // 使用docker启动容器
        String containerId = waf.getContainerId();
        Boolean online = dockerService.startContainer(containerId);
        if (!online) {
            throw new BusinessException("waf上线失败");
        }

        // 设置waf状态
        waf.setOnline(true);
        wafMapper.update(waf);
    }

    @Override
    public void offline(Long id) {
        // 查询waf
        Waf waf = wafMapper.selectById(id);
        if (waf == null) {
            throw new BusinessException("waf不存在");
        }

        // 使用docker停止容器
        String containerId = waf.getContainerId();
        Boolean offline = dockerService.stopContainer(containerId);
        if (!offline) {
            throw new BusinessException("waf下线失败");
        }

        // 设置waf状态
        waf.setOnline(false);
        wafMapper.update(waf);
    }

    @Override
    public void createWafByImageId(String name, Long imageId) {
        ImageInfo imageInfo = imageMapper.select(imageId);
        if (imageInfo == null) {
            throw new BusinessException("镜像不存在");
        }
        String containerId = dockerService.createContainer(name, imageInfo.getImageId());

        // 将waf信息存入数据库
        Waf waf = new Waf();
        waf.setName(name);
        waf.setImageId(imageId);
        waf.setContainerId(containerId);
        wafMapper.insert(waf);
    }

    /**
     * 监控waf的性能
     */
    @Scheduled(fixedDelay = 5000)
    protected void wafMonitor() {
        List<Waf> wafList = wafMapper.selectListForOnline(true);
        if (wafList == null) {
            log.error("没有waf在运行");
            return;
        }
        for (Waf waf : wafList) {
            String containerId = waf.getContainerId();
            InvocationBuilder.AsyncResultCallback<Statistics> callback = new InvocationBuilder.AsyncResultCallback<Statistics>() {
                @Override
                public void onNext(Statistics object) {
                    // cpu占用率
                    CpuStatsConfig cpuStats = object.getCpuStats();
                    Long totalUsage = cpuStats.getCpuUsage().getTotalUsage();
                    Long systemCpuUsage = cpuStats.getSystemCpuUsage();
                    BigDecimal cpu = BigDecimal.valueOf(totalUsage * 1.0 / systemCpuUsage * 100);

                    // 内存占用率
                    MemoryStatsConfig memoryStats = object.getMemoryStats();
                    Long memoryStatsUsage = memoryStats.getUsage();
                    Long limit = memoryStats.getLimit();
                    BigDecimal mem = BigDecimal.valueOf(memoryStatsUsage * 1.0 / limit * 100);

                    // 插入数据库
                    WafMonitor wafMonitor = new WafMonitor();
                    wafMonitor.setCpu(cpu);
                    wafMonitor.setMemory(mem);
                    wafMonitor.setWafId(waf.getId());
                    wafMonitorMapper.insert(wafMonitor);
                }
            };
            dockerService.statContainer(containerId, callback);
        }
    }

    @Override
    public void deleteWafById(Long id) {
        Waf waf = wafMapper.selectById(id);
        if (waf == null){
            throw new BusinessException("waf不存在");
        }
        Boolean flag = dockerService.removeContainer(waf.getContainerId());
        if (!flag){
            throw new BusinessException("删除waf失败");
        }

        // 删除数据库数据
        wafMapper.deleteById(id);
    }
}
