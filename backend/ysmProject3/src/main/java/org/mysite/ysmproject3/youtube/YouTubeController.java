package org.mysite.ysmproject3.youtube;

import com.google.api.services.youtube.YouTube;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;

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

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class YouTubeController {
    //임시 영상 ID
    private static final String VIDEO_ID = "1ifSAFCGiX8";

    private final OAuth2AuthorizedClientService authorizedClientService;
    private final YoutubeService youtubeService;

    //유튜브 채널 목록
    //http://localhost:8080/youtube/channel
    @GetMapping("/youtube/channel")
    @ResponseBody
    public List<String> callYouTubeApi(Authentication authentication) throws IOException, GeneralSecurityException {
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

        return youtubeService.getSubscriptions(accessToken); // API 응답 내용
    }

    //채널 페이지별 영상 가져오기
    //http://localhost:8080/youtube/channelvideos?channelId=UClWg5n2YST6_697s-zQSMnA&nextPageToken=CA8QAA
    @GetMapping("/youtube/channelvideos")
    @ResponseBody
    public String getChannelList(Authentication authentication, @RequestParam String channelId, @RequestParam String nextPageToken) throws IOException, GeneralSecurityException {
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

        return youtubeService.getPageVideos(accessToken, channelId, nextPageToken);
    }





    //유튜브 댓글 가져오기
    //http://localhost:8080/youtube/comments?videoId=1ifSAFCGiX8
    @GetMapping("/youtube/comments")
    @ResponseBody
    public String getVideoComments(Authentication authentication, @RequestParam String videoId) throws IOException, GeneralSecurityException {
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        OAuth2AuthorizedClient client = authorizedClientService
                .loadAuthorizedClient(
                        oauthToken.getAuthorizedClientRegistrationId(),
                        oauthToken.getName());

        if (client == null) {
            throw new IllegalStateException("OAuth2AuthorizedClient not found. Are you logged in?");
        }

        String accessToken = client.getAccessToken().getTokenValue();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        return youtubeService.getComments(accessToken, videoId);
    }













































    //유튜브 자막 추출
    //http://localhost:8080/youtube/captionlist?videoId=1ifSAFCGiX8
    @GetMapping("/youtube/captiondownload")
    @ResponseBody
    public String getYouTubeCaptionDownloader(Authentication authentication,@RequestParam String videoId) throws IOException, GeneralSecurityException {
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        OAuth2AuthorizedClient client = authorizedClientService
                .loadAuthorizedClient(
                        oauthToken.getAuthorizedClientRegistrationId(),
                        oauthToken.getName());

        if (client == null) {
            throw new IllegalStateException("OAuth2AuthorizedClient not found. Are you logged in?");
        }

        String accessToken = client.getAccessToken().getTokenValue();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        String url1 = String.format("https://www.googleapis.com/youtube/v3/captions?part=snippet&videoId=%s", videoId);

        ResponseEntity<String> response1 = restTemplate.exchange(
                url1,
                HttpMethod.GET,
                entity,
                String.class);

        JSONObject jsonResponse = new JSONObject(response1.getBody());
        JSONArray items = jsonResponse.getJSONArray("items");

        if (items != null && items.length() > 0) {
            JSONObject firstItem = items.getJSONObject(0);
            String id = firstItem.getString("id");

            String url2 = String.format("https://www.googleapis.com/youtube/v3/captions/%s?tfmt=vtt", id);

            System.out.println(url2);
            ResponseEntity<String> response2 = restTemplate.exchange(
                    url2,
                    HttpMethod.GET,
                    entity,
                    String.class);

            return response2.getBody();  // Return only the ID of the first caption
        } else {
            return "No captions found for this video.";
        }

    }

    //http://localhost:8080/youtube/captiondown?id=AUieDabfxc8sHdmoHb-04rA0aGWfOYNqh_qmv117sTjQmj0WyGQ
    @GetMapping("/youtube/captiondown")
    @ResponseBody
    public String getcaptiondown(Authentication authentication,@RequestParam String id) {
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        OAuth2AuthorizedClient client = authorizedClientService
                .loadAuthorizedClient(
                        oauthToken.getAuthorizedClientRegistrationId(),
                        oauthToken.getName());

        if (client == null) {
            throw new IllegalStateException("OAuth2AuthorizedClient not found. Are you logged in?");
        }

        String accessToken = client.getAccessToken().getTokenValue();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        String url2 = String.format("https://www.googleapis.com/youtube/v3/captions/%s?tfmt=vtt", id);

        System.out.println(url2);
        ResponseEntity<String> response2 = restTemplate.exchange(
                url2,
                HttpMethod.GET,
                entity,
                String.class);

        return response2.getBody();

    }

    //http://localhost:8080/youtube/captionlist?videoId=1ifSAFCGiX8
    @GetMapping("/youtube/captionlist")
    @ResponseBody
    public String getcaptionlist(Authentication authentication,@RequestParam String videoId) throws IOException, GeneralSecurityException {
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        OAuth2AuthorizedClient client = authorizedClientService
                .loadAuthorizedClient(
                        oauthToken.getAuthorizedClientRegistrationId(),
                        oauthToken.getName());

        if (client == null) {
            throw new IllegalStateException("OAuth2AuthorizedClient not found. Are you logged in?");
        }

        String accessToken = client.getAccessToken().getTokenValue();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        String url1 = String.format("https://www.googleapis.com/youtube/v3/captions?part=snippet&videoId=%s", videoId);

        ResponseEntity<String> response1 = restTemplate.exchange(
                url1,
                HttpMethod.GET,
                entity,
                String.class);

        JSONObject jsonResponse = new JSONObject(response1.getBody());
        JSONArray items = jsonResponse.getJSONArray("items");

        if (items != null && items.length() > 0) {
            JSONObject firstItem = items.getJSONObject(0);
            String id = firstItem.getString("id");

            return id;  // Return only the ID of the first caption
        } else {
            return "No captions found for this video.";
        }

    }



}
