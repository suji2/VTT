package org.mysite.ysmproject3.youtube;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@Service
public class YoutubeService {

    //구독아이디 리스트로 반환
    public List<String> getSubscriptions(String accessToken) throws IOException, GeneralSecurityException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://www.googleapis.com/youtube/v3/subscriptions?part=snippet&mine=true&maxResults=5",
                HttpMethod.GET,
                entity,
                String.class);

        // JSON 응답 파싱
        JSONObject jsonResponse = new JSONObject(response.getBody());
        JSONArray items = jsonResponse.getJSONArray("items");
        List<String> subscriptionsId = new ArrayList<>();

        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            JSONObject snippet = item.getJSONObject("snippet");
            JSONObject resourceId = snippet.getJSONObject("resourceId");
            subscriptionsId.add(resourceId.getString("channelId"));  // id 값만 리스트에 추가
        }
        return subscriptionsId;
    }

    //채널 동영상 출력
    public String getPageVideos(String accessToken, String channelId, String nextPageToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        if (nextPageToken != "") {

            String url = String.format("https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=10&channelId=%s&pageToken=%s", channelId, nextPageToken);

            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    String.class);

            return response.getBody();
        } else {
            String url = String.format("https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=10&channelId=%s", channelId);

            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    String.class);

            return response.getBody();
        }

    }

    //유튜브 댓글 가져오기
    //100개만 출력
    public String getComments(String accessToken, String videoId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        String url = String.format("https://www.googleapis.com/youtube/v3/commentThreads?part=snippet&videoId=%s&textFormat=plainText&maxResults=100", videoId);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class);

        return response.getBody();
    }
    

}
