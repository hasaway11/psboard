package com.example.psboard.dao;

import org.apache.ibatis.annotations.*;

@Mapper
public interface MemberBoardDao {
	@Select("select count(*) from member_post where username=#{username} and pno=#{pno} and rownum=1")
	boolean existsByUsernameAndPno(String username, Integer pno);
	
	@Insert("insert into member_post(username, pno) values(#{username}, #{pno})")
	int save(String username, Integer bno);
}
