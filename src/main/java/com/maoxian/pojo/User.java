package com.maoxian.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;

    private String username;

    private String password;

    private String email;

    private String status;

    private String delFlag;

    private Date createTime;

    private Date updateTime;

}
