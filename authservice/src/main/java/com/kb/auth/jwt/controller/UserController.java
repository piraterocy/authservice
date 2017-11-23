package com.kb.auth.jwt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kb.auth.jwt.service.UserService;


@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 * 登录
	 */
	@RequestMapping (value = "login", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	public ResponseEntity<String> login(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("userName")String userName,
			@RequestParam("password")String password){
		return userService.login(request, response, userName,password);
	}
	
	/**
	 * 刷新
	 */
	@RequestMapping (value = "refreshToken", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	public ResponseEntity<String> login(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("token")String token){
		
		return userService.refreshToken(request, response, token);
	}
	
	@RequestMapping (value = "getVersion", method = {RequestMethod.POST, RequestMethod.GET},produces="application/json;charset=UTF-8")
	public String getVersion(){
		return "0.0.6";
	}
}