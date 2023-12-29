package com.maoxian.gateway.service.impl;

import com.maoxian.gateway.exceprion.BusinessException;
import com.maoxian.gateway.mapper.SiteMapper;
import com.maoxian.gateway.pojo.Site;
import com.maoxian.gateway.service.SiteService;
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
    public List<Site> findSiteList() {
        List<Site> siteList = siteMapper.selectList();
        if (siteList.isEmpty()) {
            throw new BusinessException("站点查询失败");
        }
        return siteList;
    }

    @Override
    public void addOrModifySite(Site site) {
        //更新站点
        if (site.getId() != null) {
            int count = siteMapper.update(site);
            if (count == 0) {
                throw new BusinessException("更新站点失败");
            }
        } else {
            //增加站点
            String domain = site.getDomain();
            if (domain == null || domain.isEmpty()) {
                site.setDomain("*");
            }
            int count = siteMapper.insert(site);
            if (count == 0) {
                throw new BusinessException("增加站点失败");
            }
            siteMapper.insert(site);
        }
    }

    @Override
    public void deleteSite(Long id) {
        int count = siteMapper.delete(id);
        if (count == 0) {
            throw new BusinessException("删除站点失败");
        }
    }
}
