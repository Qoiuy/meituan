package com.cinsc.meituan.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 通过手机号注册
 */
@Data
public class UserByTel {
    @NotNull(message = "手机号必填")
    private String telephone;
    @NotNull(message = "密码必填")
    private String password;
}
