package com.example.psboard.dao;

import java.util.*;

import com.example.psboard.entity.*;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MemberDao {
	@Select("select count(*) from members where username=#{username} and rownum=1")
	boolean existsByUsername(String username);
	
	int save(Member member);

	@Select("select * from members where username=#{username} and rownum=1")
	Optional<Member> findByUsername(String username);

	@Select("select * from members where email=#{email} and rownum=1")
	Optional<String> findUsernameByEmail(String email);

	
	@Delete("delete from members where username=#{username} and rownum=1")
	int deleteByUsername(String username);

	@Update("update members set password=#{password} where username=#{username} and rownum=1")
	int updatePassword(String password, String username);
}
