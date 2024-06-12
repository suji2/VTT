package org.mysite.ysmproject3.controller;

import lombok.RequiredArgsConstructor;
import org.mysite.ysmproject3.service.CaptionSummaryService;
import org.mysite.ysmproject3.service.CommentSummaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SummaryController {
    private final CaptionSummaryService captionSummaryService;
    private final CommentSummaryService commentSummaryService;

    // 리액트 요약 출력
    @PostMapping("/summarize")
    public String getSummary(@RequestBody String videoId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:5001/summarize";

        Map<String, String> request = new HashMap<>();
        request.put("video_id", videoId);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        return response.getBody();
    }



}
