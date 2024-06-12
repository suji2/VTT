package org.mysite.ysmproject3.controller;

import lombok.RequiredArgsConstructor;
import org.mysite.ysmproject3.oauth.PrincipalDetails;
import org.mysite.ysmproject3.youtube.YoutubeService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final YoutubeService youtubeService;
    private final OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping("/api/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        System.out.println(principal.getAttributes());
        return principal.getAttributes();
    }


    @GetMapping("/test")
    public String test (Authentication authentication) {
//        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
//        PrincipalDetails principalDetails = (PrincipalDetails) oAuth2User;

//        String accessToken = principalDetails.getAccessToken();

        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        OAuth2AuthorizedClient client = authorizedClientService
                .loadAuthorizedClient(
                        oauthToken.getAuthorizedClientRegistrationId(),
                        oauthToken.getName());

        String accessToken = client.getAccessToken().getTokenValue();
        System.out.println("authentication: " + authentication);
        System.out.println("accessToken :" + accessToken);
//        System.out.println("AccessToken: " + principalDetails.getAccessToken());
//        System.out.println(youtubeService.getComments(accessToken, "1ifSAFCGiX8"));
        return "success";
    }
}
