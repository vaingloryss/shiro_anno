package com.vainglory.realm;

import com.vainglory.domain.User;
import com.vainglory.service.PermissionService;
import com.vainglory.service.RoleService;
import com.vainglory.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Set;

/**
 * 自定义realm
 */
public class CustomJdbcRealm extends AuthorizingRealm{

    private UserService userService;
    private RoleService roleService;
    private PermissionService permissionService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public PermissionService getPermissionService() {
        return permissionService;
    }

    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    /**
     * 作用：查询权限信息,并返回即可，不用任何比对
     * 何时触发：  /user/query = roles["admin"]  /user/insert= perms["user:insert"]   <shiro:hasRole  <shiro:hasPermission
     * 查询方式：通过用户名查询，该用户的 权限/角色 信息
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取当前用户的用户名
        String username = (String)principals.getPrimaryPrincipal();
        // 查询当前用户的所有 权限信息：
        // RoleService: public List<String:RoleName> queryAllRolesByUsername(String username)
        //PermissionService: public List<String:PermissionStr> queryAllPermissionsByUsername(String username)

        //RoleService roleService = ContextLoader.getCurrentWebApplicationContext().getBean("roleServiceImpl", RoleService.class);
        //PermissionService permissionService = ContextLoader.getCurrentWebApplicationContext().getBean("permissionServiceImpl", PermissionService.class);
        // 查询当前用户的权限信息
        Set<String> roles = roleService.queryAllRolenameByUsername(username);
        Set<String> perms = permissionService.queryAllPermissionByUsername(username);

        // 将查询出的信息 封装
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roles);
        simpleAuthorizationInfo.setStringPermissions(perms);
        return simpleAuthorizationInfo;
    }

    /**
     * 作用：查询身份信息，并返回即可，不用任何比对
     * 查询方式：通过用户名查询用户信息。
     * 何时触发：subject.login(token);
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取用户登录时发送来的用户名
        String username = (String)token.getPrincipal();
        // 查询用户信息： UserService:public User queryUserByUsername(String username);
        //UserService userService = ContextLoader.getCurrentWebApplicationContext().getBean("userServiceImpl", UserService.class);
        //查询到用户信息
        User user = userService.queryUserByUsername(username);
        //判断用户信息是否为null
        if(user==null){//不存在用户名
            return null;//在后续流程中会抛出异常  UnknownAccountException
        }
        // 将用户信息封装在  AuthenticationInfo 中
        return new SimpleAuthenticationInfo(
                user.getUsername(),//数据库中用户名
                user.getPassword(), //数据库中的密码
                ByteSource.Util.bytes(user.getSalt()),
                this.getName());// realm的标识
    }
}
