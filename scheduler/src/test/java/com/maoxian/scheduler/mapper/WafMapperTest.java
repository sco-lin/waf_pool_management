package com.maoxian.scheduler.mapper;

import com.maoxian.scheduler.pojo.Waf;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WafMapperTest {

    @Autowired
    private WafMapper wafMapper;
    @Test
    void selectListForStatus() {
        List<Waf> wafs = wafMapper.selectListForOnline(true);
        System.out.println(wafs);
    }

    @Test
    void selectList() {
        List<Waf> wafs = wafMapper.selectList();
        System.out.println(wafs);
    }
}