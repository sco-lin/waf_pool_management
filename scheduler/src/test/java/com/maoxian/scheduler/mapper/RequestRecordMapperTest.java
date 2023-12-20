package com.maoxian.scheduler.mapper;

import com.maoxian.scheduler.mapper.RequestRecordMapper;
import com.maoxian.scheduler.pojo.RequestRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Lin
 * @date 2023/12/19 21:21
 */
@SpringBootTest
class RequestRecordMapperTest {

    @Autowired
    private RequestRecordMapper requestRecordMapper;

    @Test
    void countForStatus() {
    }

    @Test
    void selectList() {
        List<RequestRecord> requestRecords = requestRecordMapper.selectList(0, 5, null);
        System.out.println(requestRecords);
    }

    @Test
    void selectModeById() {
    }

    @Test
    void selectRequestTimeAfterTargetTime() {
    }
}