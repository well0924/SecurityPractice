package com.spring.security.domain.dto;

import java.time.LocalDateTime;

import com.spring.security.domain.entity.LoginEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class LoginDto {
	
	private Long id;
	private String  email;
	private String password;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	
	public LoginEntity toEntity() {
		return LoginEntity
				.builder()
				.id(id)
				.email(email)
				.password(password)
				.build();
	}
	
	@Builder
	public LoginDto(Long id, String email,String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}
}
