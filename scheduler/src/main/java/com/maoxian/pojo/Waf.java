package com.maoxian.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Waf {

    private Integer id;
    private String name;
    private String ip;
    private Integer port;
    private String configUrl;
    private String status;
    private String description;
    // TODO 权重 数据库中未添加
    private Integer weight;
    // TODO url 数据库中未添加
    private String Url;
}
