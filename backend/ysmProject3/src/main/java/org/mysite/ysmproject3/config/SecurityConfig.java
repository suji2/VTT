package org.mysite.ysmproject3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/api/login/google") // CSRF 보호 비활성화
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/user/profile" ).permitAll()
                        .requestMatchers("/" ).permitAll()
                        .requestMatchers("/verify" ).permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/oauth2/authorization/google").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2Login ->
                        oauth2Login
                                .loginPage("/login")
                                .defaultSuccessUrl("/", true)  // 로그인 성공 후 리다이렉션 설정
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST")) // GET 방식 로그아웃 허용 (보안 측면에서 POST 사용 권장)
                        .logoutSuccessUrl("/") // 로그아웃 성공 시 리다이렉션할 URL
                        .invalidateHttpSession(true) // 세션 무효화
                        .deleteCookies("JSESSIONID", "remember-me") // JSESSIONID 쿠키 삭제
                );
        return http.build();
    }
}


