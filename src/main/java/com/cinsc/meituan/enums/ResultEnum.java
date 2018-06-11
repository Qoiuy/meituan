package com.cinsc.meituan.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    PARAM_ERROR(0,"参数错误"),
    NOT_EXIST(1,"用户不存在"),
    OPERATE_ERROR(2,"操作错误"),
    USER_NAME_REGISTERED(3,"用户名已被注册"),
    TELEPHONE_REGISTERED(4,"手机号已经被注册"),
    EMAIL_REGISTERED(5,"邮箱号已经被注册"),
    PASSWORD_WRONG(6,"密码错误"),
    NOT_BELONG_TO_CURRENT_USER(7,"非法操作，当前门店不属于当前用户"),
    UPDATE_USER_FAIL(8,"更新用户信息失败"),
    EMAIL_EXIST(9,"邮箱已被占用"),
    CANT_BINDING_TWO_EMAIL(10,"账户已绑定邮箱,请先进行解绑再绑定新邮箱"),
    TELEPHONE_EXIST(11,"该手机号已被占用"),
    CANT_BINDING_TWO_PHONE_NUM(12,"账户已绑定手机号,请先解绑再绑定新手机号"),
    ORDER_CANCEL_FAIL(13,"订单取消异常"),
    MAPPING_DISH_FAIL(14,"映射菜品失败"),
    IMAGE_IS_EMPTY(15,"图片文件不能为空"),
    UPLOAD_IMAGE_FAIL(16,"上传服务器菜品图片失败"),
    UPDATE_PIC_FAIL(17,"更新美团菜品图片失败"),
    REDIS_KEY_OUT_OF_TIME(18,"缓存过期失效"),
    PERMISSION_OVER(19,"权限越界,无权访问"),
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
