package com.example.psboard.dao;

import java.util.*;

import com.example.psboard.dto.*;
import org.apache.ibatis.annotations.*;

import com.example.psboard.entity.*;

@Mapper
public interface PostDao {
	public int save(Post post);
	
	public List<Map<String, Object>> findAll(int pageno, int pagesize);
	
	@Select("select count(pno) from post")
	public int count();
	
	public Optional<Map<String,Object>> findByPno(int pno);
	
	@Update("update post set read_cnt=read_cnt+1 where pno=#{pno} and rownum=1")
	public int increaseReadCnt(int pno);
	
	@Update("update post set title=#{title}, content=#{content} where pno=#{pno} and rownum=1")
	public int update(PostDto.Update dto);
	
	@Delete("delete from post where pno=#{pno} and rownum=1")
	public int deleteByPno(int pno);

	@Update("update post set good_cnt=good_cnt+1 where pno=#{pno} and rownum=1")
	public void increaseGoodCnt(int pno);

	@Select("select good_cnt from post where pno=#{pno} and rownum=1")
	public int findGoodCntByPno(int pno);
}
