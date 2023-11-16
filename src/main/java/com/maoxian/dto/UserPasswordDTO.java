package com.maoxian.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswordDTO {
    private Integer id;
    private String email;
    private String newPassword;
    private String verifyCode;
}
