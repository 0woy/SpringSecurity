package com.example.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.net.http.HttpRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/","/login", "/join", "/joinProc").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                );

        // formLogin 메소드를 통해, 허가받지 않은 경로로 진입 시 login 페이지로 유도
        // 즉, /login 경로로 이동함 + csrf 설정 진행 (개발 only)
        http
                .formLogin((auth)->auth
                        .loginPage("/login")
                        .loginProcessingUrl("/loginProc")   // front에서 정보 넘기면 security 처리
                        .permitAll()
                );

        // site 위변조 방지 설정: post 요청 시 csrf 토큰을 같이 보내주어야 진행 가능
        // 개발 시에는 csrf 토큰을 보내지 않으면 진행이 안 되므로 disable 처리
        http.csrf(auth -> auth.disable());

        return http.build();
    }

    
    // PW 해시함수로 단방향 암호화
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
