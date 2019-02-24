package com.leaf.shiro.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leaf.shiro.mapper.UserMapper;
import com.leaf.shiro.model.User;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserMapper userMapper;

	@Override
	public User findByUsername(String username) {
		return userMapper.findByUsername(username);
	}

}
