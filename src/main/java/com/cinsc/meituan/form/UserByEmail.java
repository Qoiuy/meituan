package com.cinsc.meituan.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserByEmail {
    @NotNull(message = "邮箱号必传")
    private String email;
    @NotNull(message = "密码必传")
    private String password;
    @NotNull(message = "验证码必传")
    private String code;
}
