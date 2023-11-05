package com.maoxian.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {

    private Integer id;

    private String username;

    private String email;

    private String status;

    private List<String> roles;

    private List<String> permissions;

}
