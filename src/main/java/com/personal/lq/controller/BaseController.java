package com.personal.lq.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.lq.entity.UserInfo;
import com.personal.lq.service.UserInfoService;

@RestController
public class BaseController {
	
	@Autowired
	UserInfoService userInfoService;

	@RequestMapping("/hello")
	public String hello() {
		
		return "Hello World!";
	}
	
	@RequestMapping("/userInfo")
	public List<UserInfo> userInfo() {
		
		List<UserInfo> userInfos = userInfoService.getAllUserInfo();
		
		return userInfos;
	}
	
	@RequestMapping("/userInfosec")
	public List<Map<String, Object>> userInfo2() {
		
		List<Map<String, Object>> userInfos = userInfoService.getAllUserInfo2();
		
		return userInfos;
	}
}
