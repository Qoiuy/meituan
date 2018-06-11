package com.cinsc.meituan.service.serviceImpl;

import com.cinsc.meituan.dao.UserRepository;
import com.cinsc.meituan.entity.User;
import com.cinsc.meituan.enums.ResultEnum;
import com.cinsc.meituan.exception.MyException;
import com.cinsc.meituan.service.UserService;
import com.cinsc.meituan.util.ResultVOUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public Object addUser(User user) {
        //判断用户的用户名和电话号码是否已经存在
        if(userRepository.findByUserName(user.getUserName())==null&&
                userRepository.findByTelephone(user.getTelephone())==null&&
                userRepository.findAllByEmailAddress(user.getEmailAddress())==null){
            //不存在注册的用户信息则进行用户的添加
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public User deleteUser(String username) {
        User user = userRepository.deleteByUserName(username);
        if(user==null){
            throw new MyException(ResultEnum.OPERATE_ERROR);
        }
        return user;
    }

    @Override
    public User queryUser(String username) {
        User user = userRepository.findByUserName(username);
        if(user.getUserId()==0){
            throw new MyException(ResultEnum.NOT_EXIST);
        }
        return user;
    }

    @Override
    public User getCurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        User user = queryUser(username);
        return user;
    }

    @Override
    public Object updateUser(User user) {
        User result = userRepository.save(user);
        if (result!=null){
            return ResultVOUtil.success(result);
        }
        return ResultVOUtil.error(ResultEnum.UPDATE_USER_FAIL);
    }
}
