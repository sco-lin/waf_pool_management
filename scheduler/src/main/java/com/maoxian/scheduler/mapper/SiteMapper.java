package com.maoxian.scheduler.mapper;

import com.maoxian.scheduler.pojo.Site;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Lin
 * @date 2023/12/27 21:20
 */
@Mapper
public interface SiteMapper {

    /**
     * 增加站点
     * @param site 站点信息
     * @return 增加成功
     */
    int insert(Site site);

    /**
     * 更新站点
     * @param site 站点信息
     * @return 更新成功
     */
    int update(Site site);

    /**
     * 根据id删除站点
     * @param id 站点id
     * @return 删除成功
     */
    int delete(Long id);

    /**
     * 查询所有站点
     * @return 站点信息
     */
    List<Site> selectList();

}
