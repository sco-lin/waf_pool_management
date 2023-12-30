package com.maoxian.scheduler.service.impl;

import com.maoxian.scheduler.mapper.SiteMapper;
import com.maoxian.scheduler.pojo.Site;
import com.maoxian.scheduler.service.SiteService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lin
 * @date 2023/12/27 21:39
 */
@Service
public class SiteServiceImpl implements SiteService {

    private final SiteMapper siteMapper;

    public SiteServiceImpl(SiteMapper siteMapper) {
        this.siteMapper = siteMapper;
    }

    @Override
    public void addSite(Site site) {
        siteMapper.insert(site);
    }

    @Override
    public void modifySite(Site site) {
        siteMapper.update(site);
    }

    @Override
    public void deleteSite(Long id) {
        siteMapper.delete(id);
    }

    @Override
    public List<Site> findSiteList() {
        return siteMapper.selectList();
    }
}
