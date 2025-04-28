package com.example.psboard.dao;

import java.util.*;

import com.example.psboard.dto.*;
import com.example.psboard.entity.*;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PostDao {
	int save(Post post);
	
	List<Post> findAll(int pageno, int pagesize);
	
	@Select("select count(pno) from posts")
	int count();
	
	Optional<Post> findByPno(int pno);
	
	@Update("update posts set read_cnt=read_cnt+1 where pno=#{pno} and writer!=#{writer}")
	int increaseReadCnt(int pno, String loginId);
	
	@Update("update posts set title=#{dto.title}, content=#{dto.content} where pno=#{dto.pno} and writer=#{loginId}")
	int update(PostDto.Update dto, String loginId);
	
	@Delete("delete from posts where pno=#{pno} and writer=#{loginId}")
	int delete(int pno, String loginId);

	@Update("update posts set good_cnt=good_cnt+1 where pno=#{pno} and writer=#{loginId}")
	int increaseGoodCnt(int pno, String loginId);

	@Select("select good_cnt from posts where pno=#{pno}")
	int findGoodCntByPno(int pno);

	@Update("update posts set bad_cnt=badd_cnt+1 where pno=#{pno} and writer=#{loginId}")
	int increaseBadCnt(int pno, String loginId);

	@Select("select bad_cnt from posts where pno=#{pno}")
	int findBadCntByPno(int pno);
}
