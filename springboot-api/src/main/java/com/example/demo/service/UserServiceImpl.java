package com.example.demo.service;

import com.example.demo.mapper.AcctUserMapper;
import com.example.demo.pojo.AcctUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/11/27.
 */
@Service
public class UserServiceImpl {

    @Autowired
    private AcctUserMapper userMapper;

    public AcctUser findByName(String name) {
        // 查询用户是否存在
        AcctUser bean = userMapper.findByName(name);
        if (bean != null) {
            // 查询用户信息、角色、权限
            bean = userMapper.findById(bean.getId());
        }
        return bean;
    }
/**



 */
}
