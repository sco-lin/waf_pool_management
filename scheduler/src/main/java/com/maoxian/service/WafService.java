package com.maoxian.service;

/**
 * @author Lin
 * @date 2023/12/18 23:35
 */
public interface WafService {

    /**
     * 上线waf
     * @param id waf的id
     */
    void online(Integer id);

    /**
     * 下线waf
     * @param id waf的id
     */
    void offline(Integer id);
}
