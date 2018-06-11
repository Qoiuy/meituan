package com.cinsc.meituan.controller;

import com.cinsc.meituan.entity.User;
import com.cinsc.meituan.enums.ResultEnum;
import com.cinsc.meituan.exception.MyException;
import com.cinsc.meituan.form.UserByEmail;
import com.cinsc.meituan.form.UserByName;
import com.cinsc.meituan.form.UserByTel;
import com.cinsc.meituan.service.UserService;
import com.cinsc.meituan.util.ResultVOUtil;
import com.cinsc.meituan.util.convert.UserConvert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    //通过用户名进行注册

    @RequestMapping("/registerByName")
    public Object registerByName(@Valid UserByName registerInfo,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error(bindingResult.getFieldError().getDefaultMessage());
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        User user = UserConvert.convertUserByName(registerInfo);
        Object userResult = userService.addUser(user);
        if(userResult==null){
            return ResultVOUtil.error(ResultEnum.USER_NAME_REGISTERED);
        }
        return ResultVOUtil.success(userResult);
    }

    //TODO,考虑验证码功能模块的添加
    @RequestMapping("/registerByTel")
    public Object registerByTel(@Valid UserByTel registerInfo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error(bindingResult.getFieldError().getDefaultMessage());
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        User user = UserConvert.convertUserByTel(registerInfo);
        Object userResult = userService.addUser(user);
        if(userResult==null){
            return ResultVOUtil.error(ResultEnum.TELEPHONE_REGISTERED);
        }
        return ResultVOUtil.success(userResult);
    }


    //TODO,添加邮箱发送验证码功能
    @RequestMapping("/registerByEmail")
    public Object registerByEmail(@Valid UserByEmail registerInfo,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error(bindingResult.getFieldError().getDefaultMessage());
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        User user = UserConvert.convertUserByEmail(registerInfo);
        Object userResult = userService.addUser(user);
        if (userResult==null){
            return ResultVOUtil.error(ResultEnum.EMAIL_REGISTERED);
        }
        return ResultVOUtil.success(userResult);
    }
}
