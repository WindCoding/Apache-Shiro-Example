package com.leaf.shiro;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.leaf.shiro.model.Permission;
import com.leaf.shiro.model.Role;
import com.leaf.shiro.model.User;
import com.leaf.shiro.service.UserService;

public class AuthRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		User user = (User) principals.fromRealm(this.getClass().getName()).iterator().next();
		System.out.println("-----------------"+this.getClass().getName());
		List<String> permissionList = new ArrayList<>();
		List<String> roleNameList = new ArrayList<>();
		Set<Role> roles = user.getRoles();
		if (CollectionUtils.isNotEmpty(roles)) {
			for (Role role : roles) {
				Set<Permission> permissions = role.getPermissions();
				roleNameList.add(role.getRname());
				if (CollectionUtils.isNotEmpty(permissions)) {
					for (Permission permission : permissions) {
						permissionList.add(permission.getName());
					}
				}
			}
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(permissionList);
		info.addRoles(roleNameList);
		return info;
	}

//认证登录
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		String username = usernamePasswordToken.getUsername();
		User user = userService.findByUsername(username);
		return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
	}

}
