package com.leaf.shiro.service;

import com.leaf.shiro.model.User;

public interface UserService {
	User findByUsername( String username);
}
