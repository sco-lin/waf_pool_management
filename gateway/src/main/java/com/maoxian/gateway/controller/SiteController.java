package com.maoxian.gateway.controller;

import com.maoxian.gateway.exceprion.RequestException;
import com.maoxian.gateway.pojo.Site;
import com.maoxian.gateway.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Lin
 * @date 2023/12/27 21:45
 */
@RestController
@RequestMapping("site")
public class SiteController {

    @Autowired
    private SiteService siteService;

    @PostMapping
    public void addSite(@RequestBody Site site) {
        String domain = site.getDomain();
        String server = site.getServer();
        if (domain == null || domain.isEmpty()) {
            throw new RequestException("域名不能为空");
        }
        if (server == null || server.isEmpty()) {
            throw new RequestException("防护的服务器不能为空");
        }
        siteService.addSite(site);
    }

    @PutMapping
    public void updateSite(@RequestBody Site site) {
        Long id = site.getId();
        if (id == null) {
            throw new RequestException("id不能为空");
        }
        siteService.modifySite(site);
    }

    @DeleteMapping("{id}")
    public void deleteSite(@PathVariable Long id) {
        siteService.deleteSite(id);
    }

    @GetMapping
    public List<Site> querySite() {
        return siteService.findSiteList();
    }
}
