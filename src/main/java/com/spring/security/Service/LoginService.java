package com.spring.security.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.security.Repository.LoginRepository;
import com.spring.security.domain.Role;
import com.spring.security.domain.dto.LoginDto;
import com.spring.security.domain.entity.LoginEntity;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoginService implements UserDetailsService{
	private LoginRepository loginRepository;
	
	 @Transactional
	 public Long joinUser(LoginDto memberDto) {
	     // 비밀번호 암호화
	     BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	     memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

	     return loginRepository.save(memberDto.toEntity()).getId();
	 }

	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		Optional<LoginEntity> userEntityWrapper = loginRepository.findByEmail(userEmail);
		LoginEntity userEntity = userEntityWrapper.get();
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		if (("admin@example.com").equals(userEmail)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
	}
	 
	 
}
