package com.maoxian.service.impl;

import com.maoxian.exceprion.BusinessExp;
import com.maoxian.mapper.WafMapper;
import com.maoxian.pojo.Waf;
import com.maoxian.service.WafService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WafServiceImpl implements WafService {

    @Autowired
    private WafMapper wafMapper;

    @Override
    public Waf queryWaf(Integer id) {
        Waf waf = wafMapper.queryWafById(id);
        if (waf == null) {
            throw new BusinessExp("waf查询失败");
        }
        return waf;
    }

    @Override
    public void saveOrUpdateWaf(Waf waf) {

        //更新waf
        if (waf.getId() != null) {
            int count = wafMapper.updateWaf(waf);
            if (count == 0) {
                throw new BusinessExp("waf不存在");
            }
        }

        //增加waf
        wafMapper.addWaf(waf);
    }

    @Override
    public void deleteWaf(Integer id) {
        int count = wafMapper.deleteWaf(id);
        if (count == 0) {
            throw new BusinessExp("删除waf失败");
        }
    }
}
