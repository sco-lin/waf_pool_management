package com.maoxian.service.impl;

import com.maoxian.dto.RequestChainDTO;
import com.maoxian.dto.RequestDTO;
import com.maoxian.exceprion.BusinessExp;
import com.maoxian.mapper.RequestChainMapper;
import com.maoxian.mapper.RequestMapper;
import com.maoxian.pojo.Request;
import com.maoxian.pojo.RequestChain;
import com.maoxian.service.RequestService;
import com.maoxian.vo.PageResult;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private RequestChainMapper requestChainMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PageResult<RequestDTO> findRequest(Integer pageNum, Integer paeSize, String uuid) {
        List<RequestDTO> requestDTOS;

        // 查询总数
        Integer total = requestMapper.count(uuid);

        if (total == 0) {
            requestDTOS = Collections.emptyList();
        } else {
            // 查询请求列表
            int start = (pageNum - 1) * paeSize;
            List<Request> requestList = requestMapper.selectList(start, paeSize, uuid);
            if (requestList.isEmpty()) {
                throw new BusinessExp("查询请求失败");
            }

            // List<Request>映射到List<RequestDTO>
            requestDTOS = requestList.stream().map(request -> modelMapper.map(request, RequestDTO.class)).collect(Collectors.toList());
        }

        return new PageResult<>(pageNum, paeSize, requestDTOS, total);
    }

    @Override
    public List<RequestChainDTO> findRequestChainList(Integer requestId) {
        List<RequestChain> allChains = requestChainMapper.selectList(requestId);
        if (allChains.isEmpty()) {
            throw new BusinessExp("查询请求链失败");
        }
        return buildChainStructure(allChains, 0);
    }

    /**
     * 递归创建链式结构
     *
     * @param allChains 请求链的列表
     * @param parentId  父id
     * @return 链式结构
     */
    private List<RequestChainDTO> buildChainStructure(List<RequestChain> allChains, Integer parentId) {
        // 存储链式结构
        List<RequestChainDTO> chains = new ArrayList<>();

        // 遍历请求链列表，查找与parentId匹配的请求链，将其设置为子链
        for (RequestChain chain : allChains) {
            if (chain.getParentId().equals(parentId)) {
                // RequestChain映射到RequestChainDTO
                RequestChainDTO chainDTO = modelMapper.map(chain, RequestChainDTO.class);

                // 构建子链，并添加到链式结构中
                List<RequestChainDTO> childChains = buildChainStructure(allChains, chain.getId());
                chainDTO.setChildren(childChains);
                chains.add(chainDTO);
            }
        }
        return chains;
    }
}
