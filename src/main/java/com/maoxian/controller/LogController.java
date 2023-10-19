package com.maoxian.controller;

import com.maoxian.service.LogService;
import com.maoxian.vo.LogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("log")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/query/{id}")
    List<LogVo> findLogChain(@PathVariable Integer id) {
        return logService.findLogChain(id);
    }

    @GetMapping("/query-uuid")
    List<String> findUUIDList() {
        return logService.findUUIDList();
    }

    @GetMapping("query-log/{uuid}")
    List<List<LogVo>> findLogChain(@PathVariable String uuid) {
        return logService.findLogChainByUUID(uuid);
    }
}
