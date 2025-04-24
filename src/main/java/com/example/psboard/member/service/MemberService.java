package com.example.psboard.member.service;

import com.example.psboard.member.dao.*;
import lombok.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class MemberService {
  private final MemberDao memberDao;


}
