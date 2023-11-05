package com.maoxian.controller;

import com.maoxian.exceprion.BusinessExp;
import com.maoxian.pojo.Waf;
import com.maoxian.service.WafService;
import com.maoxian.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("waf")
@PreAuthorize("hasAuthority('waf')")
public class WafController {

    @Autowired
    private WafService wafService;

    /**
     * 查询所有请求：分页+模糊查询
     *
     * @param pageNum  第几页
     * @param pageSize 页面大小
     * @param search   模糊查询字段
     * @return 查询结果
     */
    @GetMapping
    public PageResult<Waf> queryWaf(@RequestParam(defaultValue = "1") Integer pageNum,
                                    @RequestParam(defaultValue = "5") Integer pageSize,
                                    @RequestParam(defaultValue = "") String search) {
        return wafService.findWafList(pageNum, pageSize, search);
    }

    //TODO 将废弃
    @GetMapping("{id}")
    public Waf queryWaf(@PathVariable Integer id) {
        return wafService.findWafById(id);
    }

    /**
     * 增加waf
     *
     * @param waf waf信息
     */
    @PostMapping
    public void addWaf(@RequestBody Waf waf) {
        String name = waf.getName();
        String ip = waf.getIp();
        Integer port = waf.getPort();
        String configUrl = waf.getConfigUrl();
        if (name == null) {
            throw new BusinessExp("名称不能为空");
        }
        if (ip == null) {
            throw new BusinessExp("ip不能为空");
        }
        if (port == null) {
            throw new BusinessExp("端口不能为空");
        }
        if (configUrl == null) {
            throw new BusinessExp("配置地址不能为空");
        }
        wafService.addOrModifyWaf(waf);
    }

    /**
     * 修改waf
     *
     * @param waf waf信息
     */
    @PutMapping
    public void updateWaf(@RequestBody Waf waf) {
        Integer id = waf.getId();
        if (id == null) {
            throw new BusinessExp("id不能为空");
        }
        wafService.addOrModifyWaf(waf);
    }

    /**
     * 通过id删除waf
     *
     * @param id 删除条件
     */
    @DeleteMapping("{id}")
    public void deleteWaf(@PathVariable Integer id) {
        wafService.deleteWafById(id);
    }
}
