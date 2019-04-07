package com.personal.lq.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personal.lq.entity.UserInfo;
import com.personal.lq.mapper.UserMapper;
import com.personal.lq.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService{

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	JdbcTemplate db2JdbcTemplate;
	
	@Transactional
    @Override
	public List<UserInfo> getAllUserInfo(){
		
        return userMapper.getAllUserInfo();
    }
	
	@Transactional(value = "db2TransactionManager")
    @Override
	public List<Map<String, Object>> getAllUserInfo2(){
		List<Map<String, Object>> result = db2JdbcTemplate.queryForList("select * from user_info");
        return result;
    }
}
