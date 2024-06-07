package org.mysite.ysmproject3.service;

import lombok.RequiredArgsConstructor;
import org.mysite.ysmproject3.repository.CommentSummaryRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CommentSummaryService {

    private final CommentSummaryRepository commentSummaryRepository;

    //유튜브 댓글 추출 및 저장
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
    //DB에 저장된 댓글 요약 가져오기

    //댓글 요약 하기

}
