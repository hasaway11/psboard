package com.example.psboard.service;

import com.example.psboard.dao.*;
import com.example.psboard.dto.*;
import com.example.psboard.entity.*;
import com.example.psboard.exception.*;
import jakarta.validation.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@Service
public class PostService {
  private final PostDao postDao;
  private final CommentDao commentDao;
  private final MemberBoardDao memberBoardDao;

  public Map<String, Object> findAll(int pageno, int pagesize) {
    int totalcount = postDao.count();
    List<Post> posts = postDao.findAll(pageno, pagesize);
    return Map.of("posts", posts, "totalcount", totalcount, "pageno", pageno, "pagesize", pagesize);
  }

  public Post write(PostDto.Create dto, String loginId) {
    Post post = dto.toEntity(loginId);
    postDao.save(post);
    return post;
  }

  public Map<String, Object> read(int pno, String loginId) {
    if(loginId != null)
      postDao.increaseReadCnt(pno, loginId);
    Post post = postDao.findByPno(pno).orElseThrow(()->new EntityNotFoundException("Post Not Found"));
    List<Comment> comments = commentDao.findByPno(pno);
    return Map.of("post", post, "comments", comments);
  }

  public boolean update(PostDto.Update dto, String loginId) {
    return postDao.update(dto, loginId)==1;
  }

  public boolean delete(int pno, String loginId) {
    return postDao.delete(pno, loginId)==1;
  }

  public int good(int pno, String loginId) {
    postDao.increaseGoodCnt(pno, loginId);
    return postDao.findGoodCntByPno(pno);
  }

  public int bad(int pno, String loginId) {
    postDao.increaseBadCnt(pno, loginId);
    return postDao.findBadCntByPno(pno);
  }
}
