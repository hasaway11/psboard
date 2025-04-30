package com.example.psboard;

import com.example.psboard.dao.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

@SpringBootTest
public class PostDaoTest {
  @Autowired
  private PostDao postDao;

  @Test
  public void aaa() {
    System.out.println(postDao.findByPnoWithComments(111));
  }
}
