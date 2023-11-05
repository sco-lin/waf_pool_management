package com.maoxian.controller;

import com.maoxian.dto.RequestChainDTO;
import com.maoxian.dto.RequestDTO;
import com.maoxian.service.RequestService;
import com.maoxian.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("request")
public class RequestController {

    @Autowired
    private RequestService requestService;

    /**
     * 查询所有请求：分页+模糊查询
     *
     * @param pageNum  第几页
     * @param pageSize 页面大小
     * @param uuid     模糊查询字段
     * @return 查询结果
     */
    @GetMapping
    public PageResult<RequestDTO> getRequest(@RequestParam(defaultValue = "1") Integer pageNum,
                                             @RequestParam(defaultValue = "5") Integer pageSize,
                                             @RequestParam(defaultValue = "") String uuid) {
        return requestService.findRequest(pageNum, pageSize, uuid);
    }

    /**
     * 通过请求id获取请求链
     *
     * @param requestId 请求id
     * @return 请求链
     */
    @GetMapping("chain/{requestId}")
    public List<RequestChainDTO> getRequestChain(@PathVariable Integer requestId) {
        return requestService.findRequestChainList(requestId);
    }
}
