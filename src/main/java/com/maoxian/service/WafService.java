package com.maoxian.service;

import com.maoxian.pojo.Waf;
import com.maoxian.vo.PageResult;

public interface WafService {

    /**
     * 查询所有waf
     *
     * @return 所有waf信息
     */
    PageResult<Waf> findWafList(Integer pageNum, Integer pageSize, String search);

    /**
     * 通过id查询waf
     *
     * @param id 查询条件
     * @return 查询结果
     */
    Waf findWafById(Integer id);

    /**
     * 增加或更新waf
     *
     * @param waf waf信息
     */
    void addOrModifyWaf(Waf waf);

    /**
     * 通过id删除waf
     *
     * @param id 删除条件
     */
    void deleteWafById(Integer id);
}
