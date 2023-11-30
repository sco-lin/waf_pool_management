package com.maoxian.controller;

import com.maoxian.exceprion.RequestExp;
import com.maoxian.pojo.Waf;
import com.maoxian.pojo.WafStatus;
import com.maoxian.service.WafService;
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

    /**
     * 查询所有waf
     *
     * @return 查询结果
     */
    @GetMapping
    public List<Waf> queryWaf() {
        return wafService.findWafList();
    }

    @GetMapping("status/{wafId}")
    public WafStatus queryWafStatus(@PathVariable Integer wafId) {
        return wafService.findWafStatusById(wafId);
    }

    //TODO 将废弃
    @GetMapping("{id}")
    public Waf queryWaf(@PathVariable Integer id) {
        return wafService.findWafById(id);
    }

    /**
     * 增加waf
     *
     * @param waf waf信息
     */
    @PostMapping
    public void addWaf(@RequestBody Waf waf) {
        String name = waf.getName();
        String ip = waf.getIp();
        Integer port = waf.getPort();
        String configUrl = waf.getConfigUrl();
        if (name == null || name.isEmpty()) {
            throw new RequestExp("名称不能为空");
        }
        if (ip == null || ip.isEmpty()) {
            throw new RequestExp("ip不能为空");
        }
        if (port == null) {
            throw new RequestExp("端口不能为空");
        }
        if (configUrl == null || configUrl.isEmpty()) {
            throw new RequestExp("配置地址不能为空");
        }
        wafService.addOrModifyWaf(waf);
    }

    /**
     * 修改waf
     *
     * @param waf waf信息
     */
    @PutMapping
    public void updateWaf(@RequestBody Waf waf) {
        Integer id = waf.getId();
        if (id == null) {
            throw new RequestExp("id不能为空");
        }
        wafService.addOrModifyWaf(waf);
    }

    /**
     * 通过id删除waf
     *
     * @param id 删除条件
     */
    @DeleteMapping("{id}")
    public void deleteWaf(@PathVariable Integer id) {
        wafService.deleteWafById(id);
    }
}
