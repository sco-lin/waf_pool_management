package com.maoxian.scheduler.service.impl;

import com.maoxian.scheduler.exception.BusinessException;
import com.maoxian.scheduler.mapper.WafMapper;
import com.maoxian.scheduler.pojo.Waf;
import com.maoxian.scheduler.service.WafService;
import com.maoxian.scheduler.service.DockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lin
 * @date 2023/12/18 23:35
 */
@Service
public class WafServiceImpl implements WafService {

    @Autowired
    private WafMapper wafMapper;

    @Autowired
    private DockerService dockerService;

    @Override
    public void online(Long id) {
        // 查询waf
        Waf waf = wafMapper.selectById(id);
        if (waf == null) {
            throw new BusinessException("waf不存在");
        }
        String containerId = waf.getContainerId();
        Boolean online = dockerService.startContainer(containerId);
        if (!online) {
            throw new BusinessException("waf上线失败");
        }
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
        String containerId = waf.getContainerId();
        Boolean offline = dockerService.stopContainer(containerId);
        if (!offline) {
            throw new BusinessException("waf下线失败");
        }
        waf.setOnline(false);
        wafMapper.update(waf);
    }
}
