package com.example.psboard.service;

import com.example.psboard.dao.*;
import com.example.psboard.dto.*;
import com.example.psboard.entity.*;
import com.example.psboard.exception.*;
import com.example.psboard.util.*;
import jakarta.validation.*;
import lombok.*;
import org.apache.commons.io.*;
import org.springframework.http.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

import java.io.*;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MemberService {
  private final MemberDao memberDao;
  private final PasswordEncoder passwordEncoder;

  public boolean checkUsername(MemberDto.UsernameCheckRequest dto) {
    return !memberDao.existsByUsername(dto.getUsername());
  }

  public Member signUp(MemberDto.SignUpRequest dto) {
    String encodedPassword = passwordEncoder.encode(dto.getPassword());
    MultipartFile profile = dto.getProfile();
    boolean isUploadProfile = profile!=null && !profile.isEmpty();
    String base64Image = "";
    try {
      if (isUploadProfile) {
        base64Image = FileUtil.convertToBase64(profile);
      } else {
        base64Image = FileUtil.getDefaultBase64();
      }
    } catch(IOException e) {
      e.printStackTrace();
    }
    Member member = dto.toEntity(encodedPassword, base64Image);
    memberDao.save(member);
    return member;
  }

  public String searchUsername(MemberDto.UsernameSearchRequest dto) {
    return memberDao.findUsernameByEmail(dto.getEmail()).orElseThrow(()->new EntityNotFoundException("사용자를 찾을 수 없습니다"));
  }

  public boolean verifyPassword(MemberDto.PasswordCheckRequest dto, String loginId) {
    Member member = memberDao.findByUsername(loginId).orElseThrow(()->new EntityNotFoundException("사용자를 찾을 수 없습니다"));
    return passwordEncoder.matches(dto.getPassword(), member.getPassword());
  }

  public MemberDto.Read read(String loginId) {
    return memberDao.findByUsername(loginId).orElseThrow(()->new EntityNotFoundException("사용자를 찾을 수 없습니다")).toRead();
  }

  public void changePassword(MemberDto.PasswordChangeRequest dto, String loginId) {
    Member member = memberDao.findByUsername(loginId).orElseThrow(()->new EntityNotFoundException("사용자를 찾을 수 없습니다"));
    if(!passwordEncoder.matches(dto.getOldPassword(), member.getPassword()))
      throw new JobFailException("잘못된 비밀번호입니다");
    memberDao.updatePasswordByUsername(passwordEncoder.encode(dto.getNewPassword()), loginId);
  }

  public boolean resign(String loginId) {
    return memberDao.deleteByUsername(loginId)==1;
  }
}
