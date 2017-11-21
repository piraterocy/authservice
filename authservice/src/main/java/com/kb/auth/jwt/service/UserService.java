package com.kb.auth.jwt.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.kb.auth.jwt.config.Constant;
import com.kb.auth.jwt.mapper.old.UserMapper;
import com.kb.auth.jwt.model.User;
import com.kb.auth.jwt.util.JwtUtil;
import com.kb.auth.jwt.util.ResponseUtil;

import io.jsonwebtoken.Claims;

@Component
public class UserService {

	private static final Logger logger = LogManager.getLogger(UserService.class);

	@Autowired
	private JwtUtil jwt;
	
	@Autowired
	private UserMapper userDao;

	public ResponseEntity<String> login(HttpServletRequest request, HttpServletResponse response, String userName, String password) {
		try {
			//这里从数据库查看用户和密码
			User user=getUser( userName, password);
			if (user==null) {
				return ResponseUtil.exception("账号或者密码错误");
			}
			
			
			
			String subject = JwtUtil.generalSubject(user);
			String token = jwt.createJWT(Constant.JWT_ID, subject, Constant.JWT_TTL);
			String refreshToken = jwt.createJWT(Constant.JWT_ID, subject, Constant.JWT_REFRESH_TTL);
			JSONObject jo = new JSONObject();
			jo.put("token", token);
			jo.put("refreshToken", refreshToken);
			return ResponseUtil.success(jo);
		} catch (Exception e) {
			logger.error(e);
			return ResponseUtil.unKonwException();
		}
	}

	public ResponseEntity<String> refreshToken(HttpServletRequest request, HttpServletResponse response, String oldToken){
		try{
			Claims claims = jwt.parseJWT(oldToken);
			String json = claims.getSubject();
			User user = JSONObject.parseObject(json, User.class);
			String subject = JwtUtil.generalSubject(user);
			String token = jwt.createJWT(Constant.JWT_ID, subject, Constant.JWT_TTL);
			String refreshToken = jwt.createJWT(Constant.JWT_ID, subject, Constant.JWT_REFRESH_TTL);
			JSONObject jo = new JSONObject();
			jo.put("token", token);
			jo.put("refreshToken", refreshToken);
			return ResponseUtil.success(jo);
		
		} catch (Exception e) {
			logger.error(e);
			return ResponseUtil.unKonwException();
		}
	}
	
	private User getUser(String userName,String password){
		return userDao.findUser(userName, password);
	}
}