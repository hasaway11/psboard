package com.example.psboard.dao;

import com.example.psboard.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface CommentDao {
  @Insert("insert into comments(cno, content, writer, bno, write_time) values(comments_seq.nextval, #{content}, #{writer}, #{bno}, sysdate)")
  public int save(Comment comment);

  @Select("select cno, content, writer, write_time as writeTime from comments where bno=#{bno} order by cno desc")
  public List<Map<String,Object>> findByBno(int bno);

  @Delete("delete from comments where pno=#{pno}")
  public int deleteByPno(int pno);

  @Delete("delete from comments where cno=#{cno} and writer=#{writer} and rownum=1")
  public int deleteByCnoAndWriter(int cno, String writer);
}
