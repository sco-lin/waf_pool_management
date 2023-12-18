package com.maoxian.service;

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
