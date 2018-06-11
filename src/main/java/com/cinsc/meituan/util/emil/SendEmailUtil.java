package com.cinsc.meituan.util.emil;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmailUtil {
    public static String sendMessage(String emailUser,String emailPassword,
                                     String sendEmailAddress,String msg)throws Exception{
        // 创建Properties 类用于记录邮箱的一些属性
        Properties props = new Properties();
        // 表示SMTP发送邮件，必须进行身份验证
        props.put("mail.smtp.auth", "true");
        //此处填写SMTP服务器
        props.put("mail.smtp.host", "smtp.qq.com");
        //端口号，QQ邮箱给出了两个端口，但是另一个我一直使用不了，所以就给出这一个587
        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.port", 465);

        // 此处填写你的账号
        props.put("mail.user", emailUser);
        // 此处的密码就是前面说的16位STMP口令
        props.put("mail.password", emailPassword);

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(
                props.getProperty("mail.user"));
        message.setFrom(form);

        // 设置收件人的邮箱
        InternetAddress to = new InternetAddress(sendEmailAddress);
        message.setRecipient(MimeMessage.RecipientType.TO, to);

        // 设置邮件标题
        message.setSubject("测试邮件");

        // 设置邮件的内容体
        message.setContent(msg, "text/html;charset=UTF-8");

        Transport.send(message);
        return msg;
    }

    public static String sendMsgByMyEmail(String msg,String sendEmailAddress) throws Exception {
        String myEmail = "1256233771@qq.com";
        String myPassword = "eytqcbucpwkjiehj";
        String email2 = "1602827223@qq.com";
        String password2 = "acdqucktnnbuiade";
        sendMessage(myEmail,myPassword,sendEmailAddress,"您的注册验证码是:"+msg+"请在4分钟内填写.");
        return msg;
    }
}
