package org.mysite.ysmproject3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/", "/error", "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login(oauth2Login ->
                        oauth2Login
                                .loginPage("/login")
                                .successHandler(new CustomAuthenticationSuccessHandler()) // 로그인 성공 후 리다이렉션 설정
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) // GET 방식 로그아웃 허용 (보안 측면에서 POST 사용 권장)
                        .logoutSuccessUrl("/") // 로그아웃 성공 시 리다이렉션할 URL
                        .invalidateHttpSession(true) // 세션 무효화
                        .deleteCookies("JSESSIONID", "remember-me") // JSESSIONID 쿠키 삭제
                );
        return http.build();
    }
}


