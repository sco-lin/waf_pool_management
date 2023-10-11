package com.maoxian.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVo {

    private Integer id;

    private String username;

    private String email;

    private List<String> roles;

    private List<String> permissions;

}
