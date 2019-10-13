package com.vainglory.mapper;

import com.vainglory.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> findAll();

    User findById(@Param("userId") Integer userId);

    User findByUsername(@Param("username") String username);

    void add(User user);

}
