package com.example.psboard.dao;

import java.util.*;

import com.example.psboard.dto.*;
import com.example.psboard.entity.*;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PostDao {
	public int save(Post post);
	
	public List<Post> findAll(int pageno, int pagesize);
	
	@Select("select count(pno) from posts")
	public int count();
	
	public Optional<Post> findByPno(int pno);
	
	@Update("update posts set read_cnt=read_cnt+1 where pno=#{pno} and writer!=#{writer}")
	public int increaseReadCnt(int pno, String loginId);
	
	@Update("update posts set title=#{dto.title}, content=#{dto.content} where pno=#{dto.pno} and writer=#{loginId}")
	public int update(PostDto.Update dto, String loginId);
	
	@Delete("delete from posts where pno=#{pno} and writer=#{loginId}")
	public int delete(int pno, String loginId);

	@Update("update posts set good_cnt=good_cnt+1 where pno=#{pno} and writer=#{loginId}")
	public void increaseGoodCnt(int pno, String loginId);

	@Select("select good_cnt from posts where pno=#{pno}")
	public int findGoodCntByPno(int pno);

	@Update("update posts set bad_cnt=badd_cnt+1 where pno=#{pno} and writer=#{loginId}")
	public void increaseBadCnt(int pno, String loginId);

	@Select("select bad_cnt from posts where pno=#{pno}")
	public int findBadCntByPno(int pno);
}
