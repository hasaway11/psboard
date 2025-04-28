package com.example.psboard.dao;

import java.util.*;

import com.example.psboard.entity.*;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MemberDao {
	@Select("select count(*) from members where username=#{username}")
	boolean existsByUsername(String username);
	
	int save(Member member);

	@Select("select * from members where username=#{username}")
	Optional<Member> findByUsername(String username);

	@Select("select * from members where email=#{email}")
	Optional<String> findUsernameByEmail(String email);

	@Delete("delete from members where username=#{username}")
	int deleteByUsername(String username);

	@Update("update members set password=#{password} where username=#{username}")
	int updatePasswordByUsername(String password, String username);

	@Update("update members set failed_attempts=failed_attempts+1 where username=#{username}")
	int increaseFailedAttempts(String username);

	@Update("update members set is_lock=1 where username=#{username}")
	int setLock(String username);

	@Update("update members set failed_attempts=0 where username=#{username}")
	int resetFailedAttempts(String username);
}
