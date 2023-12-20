package com.maoxian.controller;

import com.maoxian.service.WafService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lin
 * @date 2023/12/18 23:35
 */
@RestController
@RequestMapping("waf")
public class WafController {

    @Autowired
    private WafService wafService;

    /**
     * 上线waf
     * @param id waf的id
     */
    @GetMapping("online/{id}")
    public void online(@PathVariable Integer id){
        wafService.online(id);
    }

    /**
     * 下线waf
     * @param id waf的id
     */
    @GetMapping("offline/{id}")
    public void offline(@PathVariable Integer id){
        wafService.offline(id);
    }
}
