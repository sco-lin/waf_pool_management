package com.maoxian.backend.controller;

import com.maoxian.backend.dto.PageResult;
import com.maoxian.backend.dto.RequestRecordDTO;
import com.maoxian.backend.dto.ScheduleRecordDTO;
import com.maoxian.backend.service.RequestService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lin
 * @date 2023/11/5 9:14
 */
@RestController
@RequestMapping("/request")
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    /**
     * 查询所有请求记录：分页+模糊查询
     *
     * @param pageNum  第几页
     * @param pageSize 页面大小
     * @param uuid     模糊查询字段
     * @return 查询结果
     */
    @GetMapping
    public PageResult<RequestRecordDTO> getRequestRecord(@RequestParam(defaultValue = "1") Integer pageNum,
                                                         @RequestParam(defaultValue = "5") Integer pageSize,
                                                         @RequestParam(defaultValue = "") String uuid) {
        return requestService.findRequest(pageNum, pageSize, uuid);
    }

    /**
     * 查询请求的调度记录
     *
     * @param requestId 请求id
     * @return 请求数组
     */
    @GetMapping("/schedule/{requestId}")
    public ScheduleRecordDTO getScheduleRecord(@PathVariable Long requestId) {
        return requestService.findScheduleRecord(requestId);
    }
}
