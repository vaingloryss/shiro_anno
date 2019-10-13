package com.vainglory.mapper;

import java.util.Set;

public interface PermissionMapper {
    public Set<String> queryAllPermissionByUsername(String username);
}
