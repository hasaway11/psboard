package com.example.psboard.member.service;

import com.example.psboard.dto.*;
import com.example.psboard.exception.*;
import com.example.psboard.member.dao.*;
import com.example.psboard.member.entity.*;
import jakarta.validation.*;
import lombok.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MemberService {
  private final MemberDao memberDao;
  private final PasswordEncoder passwordEncoder;

  public boolean checkUsername(MemberDto.UsernameCheckRequest dto) {
    return memberDao.existsByUsername(dto.getUsername());
  }

  public Member signUp(MemberDto.SignUpRequest dto) {
    String profile = "";
    String encodedPassword = passwordEncoder.encode(dto.getPassword());
    Member member = dto.toEntity(encodedPassword, profile);
    memberDao.save(member);
    return member;
  }

  public boolean checkPassword(MemberDto.PasswordCheckRequest dto, String loginId) {
    Member member = memberDao.findByUsername(loginId).orElseThrow(()->new EntityNotFoundException("아이디를 찾을 수 없습니다"));
    return passwordEncoder.matches(dto.getPassword(), member.getPassword());
  }

  public Map<String, String> read(String name) {
  }

  public void chnagePassword(MemberDto.@Valid PasswordCheckRequest dto, String name) {
  }

  public void resign(String name) {
  }
}
