package com.maoxian.service.impl;

import com.maoxian.dto.RequestDetailDTO;
import com.maoxian.dto.RequestDTO;
import com.maoxian.exceprion.BusinessExp;
import com.maoxian.mapper.RequestDetailMapper;
import com.maoxian.mapper.RequestMapper;
import com.maoxian.pojo.Request;
import com.maoxian.pojo.RequestDetail;
import com.maoxian.service.RequestService;
import com.maoxian.dto.PageResult;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private RequestDetailMapper requestDetailMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PageResult<Request> findRequest(Integer pageNum, Integer paeSize, String uuid) {
        List<Request> requestList = null;
        List<RequestDTO> requestDTOS;

        // 查询总数
        Integer total = requestMapper.count("%" + uuid + "%");

        if (total == 0) {
            requestDTOS = Collections.emptyList();
        } else {
            // 查询请求列表
            int start = (pageNum - 1) * paeSize;
            requestList = requestMapper.selectList(start, paeSize, "%" + uuid + "%");
            if (requestList.isEmpty()) {
                throw new BusinessExp("查询请求失败");
            }

            // List<Request>映射到List<RequestDTO>
            requestDTOS = requestList.stream().map(request -> modelMapper.map(request, RequestDTO.class)).collect(Collectors.toList());
        }

        return new PageResult<>(pageNum, paeSize, requestList, total);
    }

    @Override
    public RequestDetailDTO findRequestDetailList(Integer requestId) {
        Integer mode = requestMapper.selectModeById(requestId);
        List<RequestDetail> requestDetailList = requestDetailMapper.selectList(requestId);
        if (requestDetailList.isEmpty()) {
            throw new BusinessExp("查询请求数组失败");
        }
        return new RequestDetailDTO(mode, requestDetailList);
    }
}
