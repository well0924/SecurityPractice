package com.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.spring.security.Service.LoginService;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class LoginConfig extends WebSecurityConfigurerAdapter{
	
	private LoginService loginservice;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginservice).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.authorizeRequests()
         // 페이지 권한 설정
         .antMatchers("/admin/**").hasRole("ADMIN")
         .antMatchers("/user/myinfo").hasRole("MEMBER")
         .antMatchers("/**").permitAll()
     .and() // 로그인 설정
         .formLogin()
         .loginPage("/user/login")
         .defaultSuccessUrl("/user/login/result")
         .permitAll()
     .and() // 로그아웃 설정
         .logout()
         .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
         .logoutSuccessUrl("/user/logout/result")
         .invalidateHttpSession(true)
     .and()
         // 403 예외처리 핸들링
         .exceptionHandling().accessDeniedPage("/user/denied");
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
