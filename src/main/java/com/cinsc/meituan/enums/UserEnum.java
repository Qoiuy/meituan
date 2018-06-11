package com.cinsc.meituan.enums;

import lombok.Getter;

@Getter
public enum UserEnum {
    SAVE_SUCCESS(0,"添加用户成功"),
    DELETE_SUCCESS(1,"删除用户成功"),
    QUERY_SUCCESS(2,"查询用户成功")
    ;

    private Integer code;
    private String msg;

    UserEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
