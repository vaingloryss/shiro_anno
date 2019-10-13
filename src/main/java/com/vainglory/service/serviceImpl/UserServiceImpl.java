package com.vainglory.service.serviceImpl;

import com.vainglory.domain.User;
import com.vainglory.mapper.RoleMapper;
import com.vainglory.mapper.UserMapper;
import com.vainglory.service.UserService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<User> getUserList() {
        return userMapper.findAll();
    }

    @Override
    public User findByusername(String username) {
        return null;
    }

    @Override
    public User queryUserByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public void register(User user) {
        user.setSalt(UUID.randomUUID().toString());//设置随机盐
        //设置加密属性：sha256算法，随机盐，迭代1000次
        Sha256Hash sha256Hash = new Sha256Hash(user.getPassword(),user.getSalt(),1000);
        //将用户信息 (包括密码的密文 和 盐) 存入数据库
        user.setPassword(sha256Hash.toBase64());//密文采用base64格式化
        userMapper.add(user);
        System.out.println(user);
        roleMapper.add(user.getId(),3);
    }
}
