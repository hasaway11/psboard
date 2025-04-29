package com.example.psboard.util;

import com.example.psboard.dto.*;
import com.example.psboard.entity.*;

import java.util.*;

public class PagingUtil {
  public static PostDto.Pages getPagnation(int pageno, int totalcount, int pagesize, List<Post> posts) {
    int BLOCK_SIZE = 5;
    int numberOfPages = (int)Math.ceil((double)totalcount/pagesize);
    // 1~5 1    6~10 6
    int prev = ((pageno-1)/BLOCK_SIZE) * BLOCK_SIZE;
    int start = prev + 1;
    int end = prev + BLOCK_SIZE;
    int next = end + 1;
    if(end>=numberOfPages) {
      end = numberOfPages;
      next = 0;
    }
    return new PostDto.Pages(posts, prev, start, end, next, pageno);
  }
}
