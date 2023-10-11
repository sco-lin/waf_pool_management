package com.maoxian.vo;

import com.maoxian.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResult {

    private String token;

    private User user;

    private List<String> permissions;
}
