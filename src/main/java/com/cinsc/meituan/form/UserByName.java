package com.cinsc.meituan.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserByName {
    @NotNull(message = "用户名必传")
    private String username;
    @NotNull(message = "密码必传")
    private String password;
}
