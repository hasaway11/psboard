package com.example.psboard.dao;

import com.example.psboard.entity.*;
import jakarta.validation.constraints.*;
import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface CommentDao {
  @Insert("insert into comments(cno, content, writer, bno, write_time) values(comments_seq.nextval, #{content}, #{writer}, #{bno}, sysdate)")
  public int save(Comment comment);

  @Select("select * from comments where pno=#{pno} order by cno desc")
  public List<Comment> findByPno(int pno);

  @Delete("delete from comments where pno=#{pno}")
  public int deleteByPno(int pno);

  @Delete("delete from comments where cno=#{cno} and writer=#{writer} and rownum=1")
  public int deleteByCnoAndWriter(int cno, String writer);
}
