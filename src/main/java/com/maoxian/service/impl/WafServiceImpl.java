package com.maoxian.service.impl;

import com.maoxian.exceprion.BusinessExp;
import com.maoxian.mapper.WafMapper;
import com.maoxian.mapper.WafStatusMapper;
import com.maoxian.pojo.Waf;
import com.maoxian.pojo.WafStatus;
import com.maoxian.service.WafService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WafServiceImpl implements WafService {

    @Autowired
    private WafMapper wafMapper;

    @Autowired
    private WafStatusMapper wafStatusMapper;

    @Override
    public List<Waf> findWafList() {
        List<Waf> wafs = wafMapper.selectList();
        if (wafs.isEmpty()) {
            throw new BusinessExp("waf查询失败");
        }
        return wafs;
    }

    @Override
    public WafStatus findWafStatusById(Integer wafId) {
        WafStatus wafStatus = wafStatusMapper.selectByWafId(wafId);
        if (wafStatus == null) {
            throw new BusinessExp("waf状态查询失败");
        }
        return wafStatus;
    }

    @Override
    public Waf findWafById(Integer id) {
        Waf waf = wafMapper.selectById(id);
        if (waf == null) {
            throw new BusinessExp("waf查询失败");
        }
        return waf;
    }

    @Override
    public void addOrModifyWaf(Waf waf) {

        //更新waf
        if (waf.getId() != null) {
            int count = wafMapper.update(waf);
            if (count == 0) {
                throw new BusinessExp("waf不存在");
            }
        }

        //增加waf
        int count = wafMapper.insert(waf);
        if (count == 0) {
            throw new BusinessExp("增加waf失败");
        }
    }

    @Override
    public void deleteWafById(Integer id) {
        int count = wafMapper.deleteById(id);
        if (count == 0) {
            throw new BusinessExp("删除waf失败");
        }
    }
}
