package com.example.psboard.security;

import com.example.psboard.dao.*;
import com.example.psboard.entity.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

@Component
public class CustomUserDetailsService implements UserDetailsService {
  @Autowired
  private MemberDao memberDao;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Member m = memberDao.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username + " 사용자를 찾을 수 없습니다"));
    return User.builder().username(username).password(m.getPassword()).roles("USER").build();
  }
}
