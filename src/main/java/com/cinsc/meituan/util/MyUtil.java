package com.cinsc.meituan.util;

import com.cinsc.meituan.enums.ResultEnum;
import com.sankuai.sjst.platform.developer.domain.RequestSysParams;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Random;

@Slf4j
public class MyUtil {
    public static String appAuthToken = "fe75754a316db45912e6824a6499b0e9be719179a3bebbee3f43da00a55964e755a63b68b622988922b4af5140fd407e";//为测试方便可以将token定义为静态变量
    public static String ePoiId = "123456789";//测试门店的Id
    public static String signKey = "你的密钥";
    public static String developerId = "开发者Id";
    public static String uploadPath = "//home//hk//图片//test//";//上传菜品图片到自己服务器的路径

    public static RequestSysParams getParams(String appAuthToken){
        RequestSysParams params = new RequestSysParams(MyUtil.signKey,appAuthToken);
        return params;
    }

    /**
     * 生成唯一主键:
     * 时间+随机数
     * @return
     */
    public static synchronized String getUniqueKey(){
        Random random = new Random();
        Integer num = random.nextInt(900000)+100000;//生成6为随机数
        return System.currentTimeMillis()+String.valueOf(num);
    }

    /**
     * 时间格式转换
     * @param timestamp
     * @return
     */
    public static String convertTimeFormat(Long timestamp){
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(timestamp*1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(calendar.getTime());
    }

    /**
     * 生成一个随机的六位字符,用于注册用户名的设置
     * @return
     */
    public static String getRandomStr(){
        return RandomStringUtils.random(8,"abcdefghijklmnopqrstuvwxyz1234567890");
    }

    /**
     * 登录验证
     * @param username
     * @param password
     * @return
     */
    public static Object login(String username,String password){
        Subject currentUser = SecurityUtils.getSubject();

        if (!currentUser.isAuthenticated()) {
            // 把用户名和密码封装为 UsernamePasswordToken 对象
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            // rememberme
            token.setRememberMe(true);
            try {
                System.out.println("1. " + token.hashCode());
                // 执行登录.
                currentUser.login(token);
            }
            // ... catch more exceptions here (maybe custom ones specific to your application?
            // 所有认证时异常的父类.
            catch (AuthenticationException ae) {
                //unexpected condition?  error?
                System.out.println("登录失败: " + ae.getMessage());
                if (ae instanceof UnknownAccountException){
                    return ResultVOUtil.error(ResultEnum.NOT_EXIST);
                }else {
                    return ResultVOUtil.error(ResultEnum.PASSWORD_WRONG);
                }
            }
        }

        return ResultVOUtil.success();

    }
}
