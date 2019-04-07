package com.personal.lq.service;

import java.util.List;
import java.util.Map;

import com.personal.lq.entity.UserInfo;

public interface UserInfoService {

	public List<UserInfo> getAllUserInfo();
	
	public List<Map<String, Object>> getAllUserInfo2();
}
