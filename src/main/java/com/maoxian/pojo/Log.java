package com.maoxian.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log {

    private Integer id;

    private String uuid;

    private String method;

    private String sourceIp;

    private String path;

    private Integer time;

    private Integer wafId;

    private Integer parentId;
}
