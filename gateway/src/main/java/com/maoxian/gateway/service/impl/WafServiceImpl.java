package com.maoxian.gateway.service.impl;

import com.maoxian.gateway.exceprion.BusinessException;
import com.maoxian.gateway.mapper.WafMapper;
import com.maoxian.gateway.mapper.WafMonitorMapper;
import com.maoxian.gateway.pojo.Waf;
import com.maoxian.gateway.pojo.WafMonitor;
import com.maoxian.gateway.service.WafService;
import com.maoxian.gateway.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Lin
 * @date 2023/10/11 3:14
 */
@Service
public class WafServiceImpl implements WafService {

    @Autowired
    private WafMapper wafMapper;

    @Autowired
    private WafMonitorMapper wafMonitorMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Waf> findWafList() {
        List<Waf> wafList = wafMapper.selectList();
        if (wafList.isEmpty()) {
            throw new BusinessException("waf查询失败");
        }
        return wafList;
    }

    @Override
    public WafMonitor findWafMonitorById(Long id) {
        WafMonitor wafMonitor = wafMonitorMapper.selectByWafId(id);
        if (wafMonitor == null) {
            throw new BusinessException("waf监控数据查询失败");
        }
        return wafMonitor;
    }

    @Override
    public void addOrModifyWaf(Waf waf) {
        //更新waf
        if (waf.getId() != null) {
            int count = wafMapper.update(waf);
            if (count == 0) {
                throw new BusinessException("更新waf失败");
            }
        } else {
            //增加waf
            int count = wafMapper.insert(waf);
            if (count == 0) {
                throw new BusinessException("增加waf失败");
            }
        }
    }

    @Override
    public void online(Long id) {
        String url = "http://127.0.0.1:8081/waf/online/" + id;
        JsonResult jsonResult = restTemplate.getForObject(url, JsonResult.class);
        if (jsonResult == null || jsonResult.getCode() != 200) {
            throw new BusinessException("上线waf失败");
        }
    }

    @Override
    public void offline(Long id) {
        String url = "http://127.0.0.1:8081/waf/offline" + id;
        JsonResult jsonResult = restTemplate.getForObject(url, JsonResult.class);
        if (jsonResult == null || jsonResult.getCode() != 200) {
            throw new BusinessException("下线waf失败");
        }
    }

    @Override
    public void deleteWafById(Integer id) {
        int count = wafMapper.deleteById(id);
        if (count == 0) {
            throw new BusinessException("删除waf失败");
        }
    }
}
