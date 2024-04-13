package org.mysite.ysmproject3.youtube;

import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mysite.ysmproject3.domain.Video;
import org.mysite.ysmproject3.repository.VideoRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class YoutubeService {

    private final VideoRepository videoRepository;

    //특정 영상 정보 및 저장
    public String getDetailVideo(String accessToken, @RequestParam String videoId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        String url = String.format("https://www.googleapis.com/youtube/v3/search?part=snippet&type=video&q=%s", videoId);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class);

        JSONObject jsonResponse = new JSONObject(response.getBody());
        JSONArray items = jsonResponse.getJSONArray("items");
        JSONObject item = items.getJSONObject(0);
        JSONObject snippet = item.getJSONObject("snippet");
        JSONObject id = item.getJSONObject("id");
        JSONObject thumbnails = snippet.getJSONObject("thumbnails");
        JSONObject smThumbnail = thumbnails.getJSONObject("default");
        JSONObject bigThumbnail = thumbnails.getJSONObject("high");


        Video video = new Video();
        video.setId(id.getString("videoId"));
        video.setPublishedAt(snippet.getString("publishedAt"));
        video.setTitle(snippet.getString("title"));
        video.setDescription(snippet.getString("description"));
        video.setChannel(snippet.getString("channelTitle"));
        video.setSmThumbnail(smThumbnail.getString("url"));
        video.setBigThumbnail(bigThumbnail.getString("url"));
        this.videoRepository.save(video);

        return response.getBody();
    }

    //구독아이디 리스트로 반환
    public List<String> getSubscriptions(String accessToken, String nextPageToken) throws IOException, GeneralSecurityException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        String url = String.format("https://www.googleapis.com/youtube/v3/subscriptions?part=snippet&mine=true&maxResults=50&pageToken%s", nextPageToken);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
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
