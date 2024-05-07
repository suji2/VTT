package org.mysite.ysmproject3.controller;
import org.mysite.ysmproject3.domain.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Instant;
import java.util.Optional;

@Controller
public class OauthController {

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping("/verify")
    public ResponseEntity<?> verifyToken(@RequestParam("token") String token) {
        OAuth2AccessToken accessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER, token, null, null);
        System.out.println("토큰 값: " + accessToken);

        // OAuth 2.0 액세스 토큰을 검증하고 사용자 인증을 수행
        if (isValidToken(accessToken)) {
            // 사용자 인증 성공
            return ResponseEntity.ok("User authenticated successfully!");
        } else {
            // 사용자 인증 실패
            OAuth2Error error = new OAuth2Error(
                    OAuth2ErrorCodes.INVALID_TOKEN,
                    "Invalid access token",
                    "The provided access token is invalid."
            );
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }

    //유저 정보 보기 (테스트)
    @GetMapping("/user")
    @ResponseBody
    public UserDTO getUser(@AuthenticationPrincipal OAuth2User oAuth2User) {
        UserDTO user = new UserDTO();
        user.setName(oAuth2User.getAttribute("name"));
        user.setEmail(oAuth2User.getAttribute("email"));
        user.setPicture(oAuth2User.getAttribute("picture"));
        return user;
    }

    private boolean isValidToken(OAuth2AccessToken accessToken) {
        // 액세스 토큰이 null인지 확인
        if (accessToken == null) {
            return false;
        }

        // 액세스 토큰의 만료 시간을 확인하고 현재 시간과 비교
        if (accessToken.getExpiresAt() != null && accessToken.getExpiresAt().isBefore(Instant.now())) {
            return false; // 만료됨
        }

        // 다른 검증 로직을 추가할 수 있습니다.
        // 예를 들어, 토큰 서명을 확인하거나, 토큰이 유효한 사용자를 나타내는지 확인하는 등의 추가 검증이 필요할 수 있습니다.

        return true; // 모든 검증을 통과하면 토큰이 유효함
    }

}
