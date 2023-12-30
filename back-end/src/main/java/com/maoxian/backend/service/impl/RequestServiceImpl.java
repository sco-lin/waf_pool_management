package com.maoxian.backend.service.impl;

import com.maoxian.backend.dto.PageResult;
import com.maoxian.backend.dto.RequestRecordDTO;
import com.maoxian.backend.dto.ScheduleRecordDTO;
import com.maoxian.backend.exceprion.BusinessException;
import com.maoxian.backend.mapper.RequestRecordMapper;
import com.maoxian.backend.mapper.ScheduleRecordMapper;
import com.maoxian.backend.pojo.RequestRecord;
import com.maoxian.backend.pojo.ScheduleRecord;
import com.maoxian.backend.service.RequestService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lin
 * @date 2023/11/5 9:14
 */
@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRecordMapper requestMapper;

    private final ScheduleRecordMapper scheduleRecordMapper;

    private final ModelMapper modelMapper;

    public RequestServiceImpl(RequestRecordMapper requestMapper, ScheduleRecordMapper scheduleRecordMapper, ModelMapper modelMapper) {
        this.requestMapper = requestMapper;
        this.scheduleRecordMapper = scheduleRecordMapper;
        this.modelMapper = modelMapper;
    }

    @Override
    public PageResult<RequestRecordDTO> findRequest(Integer pageNum, Integer paeSize, String uuid) {
        List<RequestRecord> requestList;
        List<RequestRecordDTO> requestRecordDTOList;

        // 查询总数
        Integer total = requestMapper.count("%" + uuid + "%");

        if (total == 0) {
            requestRecordDTOList = Collections.emptyList();
        } else {
            // 查询请求列表
            int start = (pageNum - 1) * paeSize;
            requestList = requestMapper.selectList(start, paeSize, "%" + uuid + "%");

            // List<RequestRecord>映射到List<RequestRecordDTO>
            requestRecordDTOList = requestList.stream().map(request -> modelMapper.map(request, RequestRecordDTO.class)).collect(Collectors.toList());
        }

        return new PageResult<>(pageNum, paeSize, requestRecordDTOList, total);
    }

    @Override
    public ScheduleRecordDTO findScheduleRecord(Long requestId) {
        Integer mode = requestMapper.selectModeById(requestId);
        if (mode == null) {
            throw new BusinessException("查询请求调度模式失败");
        }
        List<ScheduleRecord> scheduleRecordList = scheduleRecordMapper.selectList(requestId);
        if (scheduleRecordList.isEmpty()) {
            throw new BusinessException("查询请求调度记录失败");
        }
        return new ScheduleRecordDTO(mode, scheduleRecordList);
    }
}
