package com.maoxian.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfoDTO {
    private String token;
    private UserInfoDTO userInfo;
}
