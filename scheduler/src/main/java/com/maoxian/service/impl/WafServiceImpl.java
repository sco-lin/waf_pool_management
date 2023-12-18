package com.maoxian.service.impl;

import com.maoxian.exception.BusinessExp;
import com.maoxian.mapper.WafMapper;
import com.maoxian.pojo.Waf;
import com.maoxian.service.DockerService;
import com.maoxian.service.WafService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WafServiceImpl implements WafService {

    @Autowired
    private WafMapper wafMapper;

    @Autowired
    private DockerService dockerService;

    @Override
    public void online(Integer id) {
        // 查询waf
        Waf waf = wafMapper.selectById(id);
        if (waf == null) {
            throw new BusinessExp("waf不存在");
        }
        String containerId = waf.getContainerId();
        Boolean online = dockerService.startContainer(containerId);
        if (!online) {
            throw new BusinessExp("waf上线失败");
        }
    }

    @Override
    public void offline(Integer id) {
        // 查询waf
        Waf waf = wafMapper.selectById(id);
        if (waf == null) {
            throw new BusinessExp("waf不存在");
        }
        String containerId = waf.getContainerId();
        Boolean offline = dockerService.stopContainer(containerId);
        if (!offline) {
            throw new BusinessExp("waf下线失败");
        }
    }
}
