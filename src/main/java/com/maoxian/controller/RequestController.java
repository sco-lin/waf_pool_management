package com.maoxian.controller;

import com.maoxian.dto.RequestDetailDTO;
import com.maoxian.pojo.Request;
import com.maoxian.service.RequestService;
import com.maoxian.dto.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public PageResult<Request> getRequest(@RequestParam(defaultValue = "1") Integer pageNum,
                                          @RequestParam(defaultValue = "5") Integer pageSize,
                                          @RequestParam(defaultValue = "") String uuid) {
        return requestService.findRequest(pageNum, pageSize, uuid);
    }

    /**
     * 通过请求id获取请求数组
     *
     * @param requestId 请求id
     * @return 请求数组
     */
    @GetMapping("detail/{requestId}")
    public RequestDetailDTO getRequestDetail(@PathVariable Integer requestId) {
        return requestService.findRequestDetailList(requestId);
    }
}
