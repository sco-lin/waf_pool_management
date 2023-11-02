package com.maoxian.service.impl;

import com.maoxian.exceprion.BusinessExp;
import com.maoxian.mapper.WafMapper;
import com.maoxian.pojo.Waf;
import com.maoxian.service.WafService;
import com.maoxian.vo.QueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class WafServiceImpl implements WafService {

    @Autowired
    private WafMapper wafMapper;

    private final String ipRegular = "^(((\\d)|([1-9]\\d)|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d)|([1-9]\\d)|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))$";


    @Override
    public QueryVo<Waf> queryWafList(Integer pageNum, Integer pageSize, String search) {
        List<Waf> wafs;

        String name = null;
        String ip = null;
        if (search.matches(ipRegular)) {
            ip = search;
        } else {
            name = search;
        }

        Integer total = wafMapper.selectForCount(name, ip);

        if (total == 0) {
            wafs = Collections.emptyList();
        } else {
            int start = (pageNum - 1) * pageSize;
            wafs = wafMapper.queryWafList(start, pageSize, name, ip);
        }

        return new QueryVo<>(pageNum, pageSize, wafs, total);

    }

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
