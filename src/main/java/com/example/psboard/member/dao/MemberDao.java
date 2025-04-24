package com.example.psboard.member.dao;

import java.util.*;

import com.example.psboard.member.entity.*;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MemberDao {
	@Select("select count(*) from member where username=#{username} and rownum=1")
	public boolean existsByUsername(String username);
	
	public int save(Member member);

	@Select("select * from member where username=#{username} and rownum=1")
	public Optional<Member> findByUsername(String username);
	
	@Delete("delete from member where username=#{username} and rownum=1")
	public int deleteByUsername(String username);

	@Update("update member set password=#{password} where username=#{username} and rownum=1")
	public int updatePassword(String password, String username);
}
