package com.maoxian.gateway.controller;

import com.maoxian.gateway.exceprion.RequestException;
import com.maoxian.gateway.pojo.Site;
import com.maoxian.gateway.service.SiteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Lin
 * @date 2023/12/27 21:45
 */
@RestController
@RequestMapping("/site")
public class SiteController {

    private final SiteService siteService;

    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    /**
     * 查询所有站点
     *
     * @return 站点列表
     */
    @GetMapping
    public List<Site> querySite() {
        return siteService.findSiteList();
    }

    /**
     * 增加站点
     *
     * @param site 站点信息
     */
    @PostMapping
    public void addSite(@RequestBody Site site) {
        String server = site.getServer();
        if (server == null || server.isEmpty()) {
            throw new RequestException("防护的服务器不能为空");
        }
        siteService.addOrModifySite(site);
    }

    /**
     * 修改站点
     *
     * @param site 站点信息
     */
    @PutMapping
    public void updateSite(@RequestBody Site site) {
        Long id = site.getId();
        if (id == null) {
            throw new RequestException("id不能为空");
        }
        siteService.addOrModifySite(site);
    }

    /**
     * 修改站点的防护模式
     *
     * @param id   站点id
     * @param mode 防护模式：1：串行，0：并行
     */
    @PutMapping("/{id}/{mode}")
    public void modifySiteMode(@PathVariable Long id, @PathVariable Integer mode) {
        Site site = new Site();
        site.setId(id);
        site.setMode(mode);
        siteService.addOrModifySite(site);
    }

    /**
     * 删除站点
     *
     * @param id 站点id
     */
    @DeleteMapping("/{id}")
    public void deleteSite(@PathVariable Long id) {
        siteService.deleteSite(id);
    }
}
