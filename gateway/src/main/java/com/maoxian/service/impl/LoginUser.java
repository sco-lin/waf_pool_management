package com.maoxian.service.impl;

import com.alibaba.fastjson.annotation.JSONField;
import com.maoxian.pojo.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户登录信息和权限
 *
 * @author Lin
 * @date 2023/9/21 15:30
 */
@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {

    private User user;

    //用户权限列表
    private List<String> permissions;

    //封装后的权限信息
    private List<SimpleGrantedAuthority> authorities;

    public LoginUser(User user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    @JSONField(serialize = false)//不序列化此字段
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (authorities != null) {
            return authorities;
        }

        //把permissions中的String类型的权限信息封装成SimpleGrantedAuthority
        authorities = permissions.stream()
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    //账户是否过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //账户是否被锁定
    @Override
    public boolean isAccountNonLocked() {
        return user.getStatus().equals("0");
    }

    //账户是否凭证过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //账户是否启用
    @Override
    public boolean isEnabled() {
        return true;
    }
}
