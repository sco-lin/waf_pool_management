package com.maoxian.service.impl;

import com.maoxian.exceprion.BusinessException;
import com.maoxian.mapper.WafMapper;
import com.maoxian.mapper.WafMonitorMapper;
import com.maoxian.pojo.Waf;
import com.maoxian.pojo.WafMonitor;
import com.maoxian.service.WafService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<Waf> findWafList() {
        List<Waf> wafs = wafMapper.selectList();
        if (wafs.isEmpty()) {
            throw new BusinessException("waf查询失败");
        }
        return wafs;
    }

    @Override
    public WafMonitor findWafStatusById(Integer wafId) {
        WafMonitor wafStatus = wafMonitorMapper.selectByWafId(wafId);
        if (wafStatus == null) {
            throw new BusinessException("waf状态查询失败");
        }
        return wafStatus;
    }

    @Override
    public Waf findWafById(Integer id) {
        Waf waf = wafMapper.selectById(id);
        if (waf == null) {
            throw new BusinessException("waf查询失败");
        }
        return waf;
    }

    @Override
    public void addOrModifyWaf(Waf waf) {

        //更新waf
        if (waf.getId() != null) {
            int count = wafMapper.update(waf);
            if (count == 0) {
                throw new BusinessException("waf不存在");
            }
        }

        //增加waf
        int count = wafMapper.insert(waf);
        if (count == 0) {
            throw new BusinessException("增加waf失败");
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
