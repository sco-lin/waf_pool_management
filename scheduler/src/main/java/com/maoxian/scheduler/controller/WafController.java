package com.maoxian.scheduler.controller;

import com.maoxian.scheduler.service.WafService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lin
 * @date 2023/12/18 23:35
 */
@RestController
@RequestMapping("waf")
public class WafController {

    private final WafService wafService;

    public WafController(WafService wafService) {
        this.wafService = wafService;
    }

    /**
     * 上线waf
     *
     * @param id waf的id
     */
    @GetMapping("online/{id}")
    public void online(@PathVariable Long id) {
        wafService.online(id);
    }

    /**
     * 下线waf
     *
     * @param id waf的id
     */
    @GetMapping("offline/{id}")
    public void offline(@PathVariable Long id) {
        wafService.offline(id);
    }

    /**
     * 根据镜像id创建waf
     *
     * @param name    waf名
     * @param imageId 镜像id
     */
    @GetMapping("/add/{name}/{imageId}")
    public void createWafByImageId(@PathVariable String name, @PathVariable Long imageId) {
        wafService.createWafByImageId(name, imageId);
    }

    @DeleteMapping("/{id}")
    public void deleteWaf(@PathVariable Long id){
        wafService.deleteWafById(id);
    }
}
