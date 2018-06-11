package com.cinsc.meituan.config;

import com.cinsc.meituan.dao.UserRepository;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserRepository userRepository;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        Set<String> roles = getRolesByUserName(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);

        return simpleAuthorizationInfo;
    }

    private Set<String> getRolesByUserName(String username) {
        Set<String> roles = new HashSet<>();
        roles.add(userRepository.findByUserName(username).getRole());
        return roles;
    }



    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.从主体传过来的认证信息中,获得用户名
        String username = (String) authenticationToken.getPrincipal();

        //2.通过用户名从数据库获取凭证
        String  password = getPasswordByUsername(username);
        if(password==null){
            return null;
        }
        //若密码不为空说明用户存在
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username,password,"customRealm");

        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(username));
        return authenticationInfo;
    }

    private String getPasswordByUsername(String username) {
        return userRepository.findByUserName(username).getUserPassword();
    }
}
