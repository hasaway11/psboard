package com.example.psboard;

import com.example.psboard.member.dao.*;
import com.example.psboard.member.entity.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.transaction.annotation.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemberDaoTest {
  @Autowired
  private MemberDao memberDao;

  @Test
  @Transactional
  public void saveTest() {
    Member member = Member.builder().username("spring").password("1234").email("spring@naver.com")
        .level(Level.NORMAL).role(Role.USER).profile("spring.jpg").build();
    assertEquals(1, memberDao.save(member));
  }
}
