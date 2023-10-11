package com.maoxian.controller;

import com.maoxian.pojo.Waf;
import com.maoxian.service.WafService;
import com.maoxian.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("waf")
public class WafController {

    @Autowired
    private WafService wafService;

    @GetMapping("query/{id}")
    @PreAuthorize("hasAuthority('waf:select')")
    public JsonResult queryWaf(@PathVariable Integer id) {
        Waf waf = wafService.queryWaf(id);
        return JsonResult.success(waf);
    }

    @PostMapping("add")
    @PreAuthorize("hasAuthority('waf:insert')")
    public JsonResult addWaf(Waf waf) {
        wafService.saveOrUpdateWaf(waf);
        return JsonResult.success();
    }

    @PostMapping("update")
    @PreAuthorize("hasAuthority('waf:update')")
    public JsonResult updateWaf(Waf waf) {
        wafService.saveOrUpdateWaf(waf);
        return JsonResult.success();
    }

    @GetMapping("delete/{id}")
    @PreAuthorize("hasAuthority('waf:delete')")
    public JsonResult deleteWaf(@PathVariable Integer id) {
        wafService.deleteWaf(id);
        return JsonResult.success();
    }
}
