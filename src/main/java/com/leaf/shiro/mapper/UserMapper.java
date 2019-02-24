package com.leaf.shiro.mapper;

import org.apache.ibatis.annotations.Param;

import com.leaf.shiro.model.User;

public interface UserMapper {

	User findByUsername(@Param("username") String username);
}
