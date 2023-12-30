package com.maoxian.backend.service.impl;

import com.maoxian.backend.exceprion.BusinessException;
import com.maoxian.backend.mapper.WafMapper;
import com.maoxian.backend.mapper.WafMonitorMapper;
import com.maoxian.backend.pojo.Waf;
import com.maoxian.backend.pojo.WafMonitor;
import com.maoxian.backend.service.WafService;
import com.maoxian.backend.util.JsonResult;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Lin
 * @date 2023/10/11 3:14
 */
@Service
public class WafServiceImpl implements WafService {

    private final WafMapper wafMapper;

    private final WafMonitorMapper wafMonitorMapper;

    private final RestTemplate restTemplate;

    public WafServiceImpl(WafMapper wafMapper, WafMonitorMapper wafMonitorMapper, RestTemplate restTemplate) {
        this.wafMapper = wafMapper;
        this.wafMonitorMapper = wafMonitorMapper;
        this.restTemplate = restTemplate;
    }

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
        String url = "http://127.0.0.1:8081/waf/offline/" + id;
        JsonResult jsonResult = restTemplate.getForObject(url, JsonResult.class);
        if (jsonResult == null || jsonResult.getCode() != 200) {
            throw new BusinessException("下线waf失败");
        }
    }

    @Override
    public void createWafByImageId(String name, Long imageId) {
        String url = "http://127.0.0.1:8081/waf/add/" + name + "/" + imageId;
        JsonResult jsonResult = restTemplate.getForObject(url, JsonResult.class);
        if (jsonResult == null || jsonResult.getCode() != 200) {
            throw new BusinessException("创建waf实例失败");
        }
    }

    @Override
    public void deleteWafById(Integer id) {
        String url = "http://127.0.0.1:8081/waf/" + id;
        ResponseEntity<JsonResult> response = restTemplate.exchange(url, HttpMethod.DELETE, null, JsonResult.class);
        JsonResult jsonResult = response.getBody();
        if (jsonResult == null || jsonResult.getCode() != 200) {
            throw new BusinessException("删除waf失败");
        }
    }
}
