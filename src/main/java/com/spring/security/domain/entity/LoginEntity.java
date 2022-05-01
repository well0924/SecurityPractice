package com.spring.security.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 20, nullable =  false)
	private String email;
	
	@Column(length = 100, nullable = false)
	private String password;
	
	@Builder
	public LoginEntity(Long id, String email, String password) {
		this.id =id;
		this.email = email;
		this.password = password;
	}
}
