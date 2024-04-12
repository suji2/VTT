package org.mysite.ysmproject3.youtube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController

public class YouTubeController {

    //임시 영상 ID
    private static final String VIDEO_ID = "1ifSAFCGiX8";

    @Autowired
    private final OAuth2AuthorizedClientService authorizedClientService;

    public YouTubeController(OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
    }

    //유튜브 채널 목록
    //http://localhost:8080/youtube/channel
    @GetMapping("/youtube/channel")
    @ResponseBody
    public String callYouTubeApi(Authentication authentication) {
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        OAuth2AuthorizedClient client = authorizedClientService
                .loadAuthorizedClient(
                        oauthToken.getAuthorizedClientRegistrationId(),
                        oauthToken.getName());
        if (client == null) {
            // 적절한 예외 처리 또는 로그 출력
            throw new IllegalStateException("OAuth2AuthorizedClient not found. Are you logged in?");
        }

        String accessToken = client.getAccessToken().getTokenValue();
        // 이제 accessToken을 사용하여 YouTube Data API 호출 가능
        // 메서드 내부에서
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://www.googleapis.com/youtube/v3/subscriptions?part=snippet&mine=true&maxResults=25", // YouTube Data API 요청 URL 예시
                HttpMethod.GET,
                entity,
                String.class);

        return response.getBody(); // API 응답 내용
//        return "test";
    }

    //유튜브 댓글 가져오기
    //http://localhost:8080/youtube/comments?videoId=1ifSAFCGiX8
    @GetMapping("/youtube/comments")
    @ResponseBody
    public String getVideoComments(Authentication authentication, @RequestParam String videoId) {
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        OAuth2AuthorizedClient client = authorizedClientService
                .loadAuthorizedClient(
                        oauthToken.getAuthorizedClientRegistrationId(),
                        oauthToken.getName());

        if (client == null) {
            throw new IllegalStateException("OAuth2AuthorizedClient not found. Are you logged in?");
        }

        String accessToken = client.getAccessToken().getTokenValue();

        System.out.println(accessToken);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        String url = String.format("https://www.googleapis.com/youtube/v3/commentThreads?part=snippet&videoId=%s&textFormat=plainText&maxResults=20", videoId);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class);

        return response.getBody();
    }




}
