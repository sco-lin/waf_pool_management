package com.maoxian.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Waf {

    private Long id;

    private String name;

    private String ip;

    private Long port;

    private String version;

    private String status;

    private String description;
}
