package com.vainglory.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Set;

public interface RoleMapper {
    public Set<String> queryAllRolenameByUsername(@Param("username") String username);

    void add(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
}
