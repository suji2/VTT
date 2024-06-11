package org.mysite.ysmproject3.youtube;

import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mysite.ysmproject3.domain.Video;
import org.mysite.ysmproject3.exception.CustomException;
import org.mysite.ysmproject3.repository.VideoRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class YoutubeService {

    private final VideoRepository videoRepository;


    //특정 영상 정보 및 저장
    public String getDetailVideo(String accessToken, String videoId) {
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

        // 데이터베이스에서 비디오ID로 조회
        Optional<Video> existingVideo = videoRepository.findById(id.getString("videoId"));
        if (existingVideo.isPresent()) {
            return response.getBody(); // 이미 데이터베이스에 존재하는 경우
        }

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

    //채널아이디 리스트로 반환
    public String getSubscriptions(String accessToken, String nextPageToken) throws IOException, GeneralSecurityException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        String urlTemplate = "https://www.googleapis.com/youtube/v3/subscriptions?part=snippet&mine=true&maxResults=50";
        if (nextPageToken != null && !nextPageToken.isEmpty()) {
            urlTemplate += "&pageToken=" + nextPageToken;  // nextPageToken을 URL에 추가
        }

        ResponseEntity<String> response = restTemplate.exchange(
                urlTemplate,
                HttpMethod.GET,
                entity,
                String.class);

        return response.getBody();

        /*// JSON 응답 파싱
        JSONObject jsonResponse = new JSONObject(response.getBody());
        JSONArray items = jsonResponse.getJSONArray("items");
        List<String> subscriptionsId = new ArrayList<>();

        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            JSONObject snippet = item.getJSONObject("snippet");
            JSONObject resourceId = snippet.getJSONObject("resourceId");
            subscriptionsId.add(resourceId.getString("channelId"));  // id 값만 리스트에 추가
        }
        return subscriptionsId;*/
    }

    //채널 동영상 출력
    public String getPageVideos(String accessToken, String channelId, String nextPageToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        // nextPageToken이 null이거나 빈 문자열인지 체크
        if (nextPageToken != null && !nextPageToken.isEmpty()) {
            // nextPageToken이 제공된 경우
            String url = String.format("https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=10&channelId=%s&pageToken=%s", channelId, nextPageToken);

            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    String.class);

            return response.getBody();
        } else {
            // nextPageToken이 null이거나 빈 문자열인 경우
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
    //모든 댓글 출력되게끔 수정해야함
    public String getComments(String accessToken, String videoId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        String url = String.format("https://www.googleapis.com/youtube/v3/commentThreads?part=snippet&videoId=%s&textFormat=plainText&maxResults=100", videoId);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    String.class);
            return response.getBody();
        } catch (RestClientException e) {
            // 로깅 및 사용자에게 친절한 에러 메시지 반환
            throw new CustomException("Failed to retrieve comments due to an error: " + e.getMessage(), e);
        }

    }

}
