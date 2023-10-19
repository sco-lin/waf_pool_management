package com.maoxian.vo;

import com.maoxian.pojo.Waf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogVo {

    private Integer id;

    private String uuid;

    private String method;

    private String sourceIp;

    private String path;

    private Integer time;

    private Waf waf;
}
