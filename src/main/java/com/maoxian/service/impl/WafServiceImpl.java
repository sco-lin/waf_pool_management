package com.maoxian.service.impl;

import com.maoxian.exceprion.BusinessExp;
import com.maoxian.mapper.WafMapper;
import com.maoxian.pojo.Waf;
import com.maoxian.service.WafService;
import com.maoxian.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class WafServiceImpl implements WafService {

    @Autowired
    private WafMapper wafMapper;

    @Override
    public PageResult<Waf> findWafList(Integer pageNum, Integer pageSize, String search) {

        String name = null;
        String ip = null;
        String ipRegular = "^(((\\d)|([1-9]\\d)|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d)|([1-9]\\d)|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))$";

        // 通过正则表达式判断模糊查询的字段
        if (search.matches(ipRegular)) {
            ip = search;
        } else {
            name = search;
        }

        List<Waf> wafs;

        // 查询总数
        Integer total = wafMapper.count(name, ip);

        if (total == 0) {
            wafs = Collections.emptyList();
        } else {
            // 查询waf列表
            int start = (pageNum - 1) * pageSize;
            wafs = wafMapper.selectList(start, pageSize, name, ip);
        }
        if (wafs.isEmpty()) {
            throw new BusinessExp("waf查询失败");
        }

        return new PageResult<>(pageNum, pageSize, wafs, total);
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
