package com.maoxian.scheduler.service;

/**
 * @author Lin
 * @date 2023/12/18 23:35
 */
public interface WafService {

    /**
     * 上线waf
     * @param id waf的id
     */
    void online(Long id);

    /**
     * 下线waf
     * @param id waf的id
     */
    void offline(Long id);
}
