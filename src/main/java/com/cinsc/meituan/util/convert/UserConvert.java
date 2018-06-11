package com.cinsc.meituan.util.convert;

import com.cinsc.meituan.entity.User;
import com.cinsc.meituan.form.UserByEmail;
import com.cinsc.meituan.form.UserByName;
import com.cinsc.meituan.form.UserByTel;
import com.cinsc.meituan.util.MyUtil;
import org.apache.shiro.crypto.hash.Md5Hash;

public class UserConvert {

    /**
     * 手机号注册用户转换器
     * @param userByTel
     * @return
     */
    public static User convertUserByTel(UserByTel userByTel){
        User user = new User();
        user.setUserName(MyUtil.getRandomStr());//定义随机的用户名
        user.setTelephone(userByTel.getTelephone());
        Md5Hash md5Hash = new Md5Hash(userByTel.getPassword(),user.getUserName());
        user.setUserPassword(md5Hash.toString());
        user.setRole("user");//默认设置用户角色为普通用户
        return user;
    }

    public static User convertUserByName(UserByName userByName){
        User user = new User();
        user.setUserName(userByName.getUsername());
        //对密码进行加密
        Md5Hash md5Hash = new Md5Hash(userByName.getPassword(),user.getUserName());
        user.setUserPassword(md5Hash.toString());
        user.setRole("user");
        return user;
    }

    public static User convertUserByEmail(UserByEmail userByEmail){
        User user = new User();
        user.setUserName(MyUtil.getRandomStr());
        user.setEmailAddress(userByEmail.getEmail());
        Md5Hash md5Hash = new Md5Hash(userByEmail.getPassword(),user.getUserName());
        user.setUserPassword(md5Hash.toString());
        user.setRole("user");
        return user;
    }

}
