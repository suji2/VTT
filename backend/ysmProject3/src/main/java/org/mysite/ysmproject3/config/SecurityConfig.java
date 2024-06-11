package org.mysite.ysmproject3.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.mysite.ysmproject3.oauth.PrincipalDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/test").authenticated()
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults())
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .failureHandler(new AuthenticationFailureHandler() {
                            @Override
                            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                                log.info("securityConfig : {}", request);
                                log.error("Authentication failed: {}", exception.getMessage(), exception); // 예외 메시지 및 스택 트레이스를 로그로 기록
                                Map<String, String> failData = new HashMap<>();
                                failData.put("message", "선문대 이메일이 아닙니다.");

                                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                response.setContentType("application/json;charset=UTF-8");
                                response.getWriter().write(objectMapper().writeValueAsString(failData));
                            }
                        })
                        .successHandler(new AuthenticationSuccessHandler() {
                            @Override
                            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                                OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
                                PrincipalDetails principalDetails = (PrincipalDetails) oAuth2User;

                                log.info("[authentication] : {}", oAuth2User.getAttributes());
                                log.info("Access Token: {}", principalDetails.getAccessToken());

                                String accessToken = principalDetails.getAccessToken();

                                //쿠키 생성 및 설정
                                Cookie accessTokenCookie = new Cookie("ACCESS_TOKEN", accessToken);
                                accessTokenCookie.setHttpOnly(true); // HttpOnly 설정
                                accessTokenCookie.setSecure(true); // Secure 설정 (HTTPS 환경에서만 전송)
                                accessTokenCookie.setPath("/"); // 쿠키 경로 설정
                                accessTokenCookie.setMaxAge(60 * 60 * 24); // 쿠키 유효 기간 설정 (예: 1일)

                                // 쿠키 추가
                                response.addCookie(accessTokenCookie);

                                response.sendRedirect("http://localhost:3000/main?user=google_" + oAuth2User.getAttribute("sub"));
                            }
                        })
                )
                .logout(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
