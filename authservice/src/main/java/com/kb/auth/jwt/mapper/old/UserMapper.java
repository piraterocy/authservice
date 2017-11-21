package com.kb.auth.jwt.mapper.old;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.kb.auth.jwt.model.User;

public interface UserMapper {
	//"" ;
	
	
		@Select("select t.fsuserid,t.fsopname from FS_USER_TC t where t.fsopcode=#{userName} and t.password=#{password}")
		@Results({
			@Result(property = "userId", column = "fsuserid"),
			@Result(property = "userName", column = "fsopname")
		})
	    User findUser(@Param(value = "userName") String userName,@Param(value = "password") String password);
}
