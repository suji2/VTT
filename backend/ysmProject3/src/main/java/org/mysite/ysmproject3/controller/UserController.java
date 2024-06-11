package org.mysite.ysmproject3.controller;

import lombok.RequiredArgsConstructor;
import org.mysite.ysmproject3.oauth.PrincipalDetails;
import org.mysite.ysmproject3.youtube.YoutubeService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final YoutubeService youtubeService;

    @GetMapping("/api/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        System.out.println(principal.getAttributes());
        return principal.getAttributes();
    }



    @GetMapping("/test")
    public String getTest(Authentication authentication) {
        if (authentication == null) {
            return "Authentication is null";
        }

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        PrincipalDetails principalDetails = (PrincipalDetails) oAuth2User;
        System.out.println("oAuth2User: " + oAuth2User);
        System.out.println("accessToken: "+principalDetails.getAccessToken());
        String accessToken = principalDetails.getAccessToken();
        System.out.println(youtubeService.getComments(accessToken, "1ifSAFCGiX8"));
        return "Hello from the test endpoint!";
    }
}
