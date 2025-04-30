package com.example.psboard;

import com.example.psboard.dto.*;
import com.example.psboard.util.*;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.*;

import java.util.*;

@SpringBootTest
public class PagingUtilTest {
  @Test
  public void testRun() {
    for(int p=1; p<=13; p++) {
      PostDto.Pages result = PagingUtil.getPagnation(p, 123, 10, null);
      System.out.println(result);
    }
  }
}
