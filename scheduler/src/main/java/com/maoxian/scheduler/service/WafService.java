package com.maoxian.scheduler.service;

/**
 * @author Lin
 * @date 2023/12/18 23:35
 */
public interface WafService {

    /**
     * 上线waf
     *
     * @param id waf的id
     */
    void online(Long id);

    /**
     * 下线waf
     *
     * @param id waf的id
     */
    void offline(Long id);

    /**
     * 根据镜像id创建waf
     *
     * @param name    waf名称
     * @param imageId 镜像id
     */
    void createWafByImageId(String name, Long imageId);

    /**
     * 删除waf
     *
     * @param id id
     */
    void deleteWafById(Long id);
}
