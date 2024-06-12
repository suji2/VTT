package org.mysite.ysmproject3.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.text.StringEscapeUtils;
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
    @PostMapping("/youtube/summarize")
    public String getSummary() {
        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://192.168.34.04:5001/summarize?videoId=" + "RMnVSr7KC3I";
        String url = "http://192.168.34.04:5001/summarize";

        Map<String, String> request = new HashMap<>();
        request.put("video_id", "RMnVSr7KC3I");

        ResponseEntity<String> response = restTemplate.postForEntity(url, request,String.class);

        // 유니코드 이스케이프 문자열을 디코딩
        String decodedResponse = StringEscapeUtils.unescapeJava(response.getBody());

        System.out.println("flask 데이터: "+decodedResponse);

        return decodedResponse;

    }



}
