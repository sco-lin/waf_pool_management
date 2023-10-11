package com.maoxian.service.impl;

import com.maoxian.exceprion.BusinessExp;
import com.maoxian.mapper.PermMapper;
import com.maoxian.service.PermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermServiceImpl implements PermService {

    @Autowired
    private PermMapper permMapper;

    @Override
    public List<String> queryPermByUserId(Integer userId) {
        List<String> perm = permMapper.queryPermByUserId(userId);
        if (perm == null) {
            throw new BusinessExp("查询失败");
        }
        return perm;
    }
}
