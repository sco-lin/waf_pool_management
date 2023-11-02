package com.maoxian.controller;

import com.maoxian.pojo.Waf;
import com.maoxian.service.WafService;
import com.maoxian.vo.QueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("waf")
@PreAuthorize("hasAuthority('waf')")
public class WafController {

    @Autowired
    private WafService wafService;

    @GetMapping
    public QueryVo<Waf> queryWaf(@RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "5") Integer pageSize,
                                 @RequestParam(defaultValue = "") String search) {
        return wafService.queryWafList(pageNum, pageSize, search);
    }

    //TODO 将废弃
    @GetMapping("{id}")
    public Waf queryWaf(@PathVariable Integer id) {
        return wafService.queryWaf(id);
    }

    @PostMapping("add")
    public void addWaf(Waf waf) {
        wafService.saveOrUpdateWaf(waf);
    }

    @PutMapping("update")
    public void updateWaf(Waf waf) {
        wafService.saveOrUpdateWaf(waf);
    }

    @DeleteMapping("delete/{id}")
    public void deleteWaf(@PathVariable Integer id) {
        wafService.deleteWaf(id);
    }
}
