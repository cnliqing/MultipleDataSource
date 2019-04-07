package com.personal.lq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.personal.lq.entity.UserInfo;

@Mapper
public interface UserMapper {

	public List<UserInfo> getAllUserInfo();
}
