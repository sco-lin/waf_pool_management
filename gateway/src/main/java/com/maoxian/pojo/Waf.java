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
}
