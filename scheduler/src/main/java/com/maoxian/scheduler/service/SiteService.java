package com.maoxian.scheduler.service;

import com.maoxian.scheduler.pojo.Site;

import java.util.List;

/**
 * @author Lin
 * @date 2023/12/27 21:33
 */
public interface SiteService {

    /**
     * 增加站点
     * @param site 站点信息
     */
    void addSite(Site site);

    /**
     * 修改站点
     * @param site 站点信息
     */
    void modifySite(Site site);

    /**
     * 删除站点
     * @param id 站点id
     */
    void deleteSite(Long id);

    /**
     * 查询所有站点信息
     * @return 站点信息
     */
    List<Site> findSiteList();
}
