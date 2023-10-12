package com.maoxian.controller;

import com.maoxian.pojo.Waf;
import com.maoxian.service.WafService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("waf")
@PreAuthorize("hasAuthority('waf')")
public class WafController {

    @Autowired
    private WafService wafService;

    @GetMapping("query/{id}")
    public Waf queryWaf(@PathVariable Integer id) {
        return wafService.queryWaf(id);
    }

    @PostMapping("add")
    public void addWaf(Waf waf) {
        wafService.saveOrUpdateWaf(waf);
    }

    @PostMapping("update")
    public void updateWaf(Waf waf) {
        wafService.saveOrUpdateWaf(waf);
    }

    @GetMapping("delete/{id}")
    public void deleteWaf(@PathVariable Integer id) {
        wafService.deleteWaf(id);
    }
}
