package com.example.psboard.service;

import com.example.psboard.dao.*;
import com.example.psboard.dto.*;
import com.example.psboard.entity.*;
import com.example.psboard.exception.*;
import com.example.psboard.util.*;
import jakarta.validation.*;
import lombok.*;
import org.apache.commons.io.*;
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
    MultipartFile profile = dto.getProfile();
    boolean isUploadProfile = profile!=null && !profile.isEmpty();
    String encodedPassword = passwordEncoder.encode(dto.getPassword());
    String profileName = BoardConstant.DEFAULT_PROFILE_NAME;
    if(isUploadProfile) {
      String ext = FilenameUtils.getExtension(profile.getOriginalFilename());
      profileName = dto.getUsername() + "." + ext;
      try {
        File target = new File(BoardConstant.PROFILE_FOLDER + profileName);
        profile.transferTo(target);
        profileName = dto.getUsername() + "." + ext;
      } catch(IOException e) {
        e.printStackTrace();
      }
    }
    Member member = dto.toEntity(encodedPassword, profileName);
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
    memberDao.updatePassword(passwordEncoder.encode(dto.getNewPassword()), loginId);
  }

  public boolean resign(String loginId) {
    return memberDao.deleteByUsername(loginId)==1;
  }
}
