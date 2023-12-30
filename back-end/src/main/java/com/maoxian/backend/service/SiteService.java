package com.maoxian.backend.service;

import com.maoxian.backend.pojo.Site;

import java.util.List;

/**
 * @author Lin
 * @date 2023/12/27 21:33
 */
public interface SiteService {

    /**
     * 查询所有站点信息
     *
     * @return 站点信息
     */
    List<Site> findSiteList();

    /**
     * 增加或更新站点
     *
     * @param site 站点信息
     */
    void addOrModifySite(Site site);

    /**
     * 删除站点
     *
     * @param id 站点id
     */
    void deleteSite(Long id);
}
