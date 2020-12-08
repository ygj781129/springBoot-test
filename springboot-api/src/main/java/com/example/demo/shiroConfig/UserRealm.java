package com.example.demo.shiroConfig;

/**
 * Created by Administrator on 2019/11/27.
 */

import com.example.demo.pojo.AcctAuthority;
import com.example.demo.pojo.AcctRole;
import com.example.demo.pojo.AcctUser;
import com.example.demo.service.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 自定义Realm，实现授权与认证
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserServiceImpl userService;

    /**
     * 用户授权
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principalCollection) {

        System.out.println("===执行授权===");

        Subject subject = SecurityUtils.getSubject();
        AcctUser user = (AcctUser)subject.getPrincipal();
        if(user != null){
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            // 角色与权限字符串集合
            Collection<String> rolesCollection = new HashSet<>();
            Collection<String> premissionCollection = new HashSet<>();
            // 读取并赋值用户角色与权限
            Set<AcctRole> roles = user.getRoles();
            for(AcctRole role : roles){
                rolesCollection.add(role.getName());
                Set<AcctAuthority> permissions = role.getPermissions();
                for (AcctAuthority permission : permissions){
                    premissionCollection.add(permission.getUrl());
                }
                info.addStringPermissions(premissionCollection);
            }
            info.addRoles(rolesCollection);
            System.out.println("rolescollection:"+rolesCollection);
            System.out.println("info:"+info);
            return info;
        }
        return null;
    }

    /**
     * 用户认证
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("===执行认证===");
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        AcctUser bean = userService.findByName(token.getUsername());
        //4.若用户不行存在，可以抛出UnknownAccountException
        if(bean==null){
            throw new UnknownAccountException("用户不存在");
        }
        //5.若用户被锁定，可以抛出LockedAccountException
//        if(u.isLocked()){
//            throw new LockedAccountException("用户被锁定");
//        }
        //7.根据用户的情况，来构建AuthenticationInfo对象,通常使用的实现类为SimpleAuthenticationInfo
        //以下信息是从数据库中获取的
        //1)principal：认证的实体信息，可以是username，也可以是数据库表对应的用户的实体对象
        Object principal = bean.getName();
        //2)credentials：密码
        Object credentials = bean.getPassword();
        //3)realmName：当前realm对象的name，调用父类的getName()方法即可
        //String realmName = bean.getLoginName();
        ByteSource credentialsSalt = ByteSource.Util.bytes(principal);//加点盐
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(bean,credentials,credentialsSalt,"");
        System.out.println(info.toString());
        return info;
    }

    // 模拟Shiro用户加密，假设用户密码为123456
    public static void main(String[] args){
        // 用户名
        String username = "";
        // 用户密码
        String password = "123456";
        // 加密方式
        String hashAlgorithName = "MD5";
        // 加密次数
        int hashIterations = 1024;//加密次数为1时并且不加盐 跟普通的MD5生成一样
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);//加点盐
        Object obj = new SimpleHash(hashAlgorithName, password,
                null, hashIterations);
        System.out.println(credentialsSalt);
        System.out.println(obj);
    }


    //执行授权
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
//        // TODO Auto-generated method stub
//        System.out.println("授权");
//        //获取当前登录用户
//        Subject subject = SecurityUtils.getSubject();
//        AcctUser user = (AcctUser) subject.getPrincipal();
//        //给资源授权
//        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        simpleAuthorizationInfo.addStringPermission(user.getPerms());
//        return simpleAuthorizationInfo;
//    }
//    //执行认证逻辑
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
//        // TODO Auto-generated method stub
//        System.out.println("认证");
//
//        //shiro判断逻辑
//        UsernamePasswordToken user = (UsernamePasswordToken) arg0;
//        AcctUser realUser = new AcctUser();
//        realUser.setName(user.getUsername());
//        realUser.setPassword(String.copyValueOf(user.getPassword()));
//        AcctUser newUser = userService.findUser(realUser);
//        //System.out.println(user.getUsername());
//        if(newUser == null){
//            //用户名错误
//            //shiro会抛出UnknownAccountException异常
//            return null;
//        }
//
//        return new SimpleAuthenticationInfo(newUser,newUser.getPassword(),"");
//    }


}

