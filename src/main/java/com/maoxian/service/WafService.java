package com.maoxian.service;

import com.maoxian.pojo.Waf;
import com.maoxian.vo.QueryVo;

public interface WafService {

    /**
     * 查询所有waf
     *
     * @return 所有waf信息
     */
    QueryVo<Waf> queryWafList(Integer pageNum, Integer pageSize, String search);

    /**
     * 通过id查询waf
     *
     * @param id id
     * @return 查询结果
     */
    Waf queryWaf(Integer id);

    /**
     * 添加或更新waf
     *
     * @param waf waf信息
     */
    void saveOrUpdateWaf(Waf waf);

    /**
     * 删除waf
     *
     * @param id id
     */
    void deleteWaf(Integer id);
}
