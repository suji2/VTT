package org.mysite.ysmproject3.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.text.StringEscapeUtils;
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

    @PostMapping("/youtube/summarize")
    public Map<String, String> getSummary(@RequestBody Map<String, String> request) {
        String videoId = request.get("video_id");
        if (videoId == null || videoId.isEmpty()) {
            throw new IllegalArgumentException("Video ID is required");
        }

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://192.168.34.4:5001/summarize";

        Map<String, String> flaskRequest = new HashMap<>();
        flaskRequest.put("video_id", videoId);

        ResponseEntity<Map> response = restTemplate.postForEntity(url, flaskRequest, Map.class);

        Map<String, String> responseBody = response.getBody();
        if (responseBody == null || responseBody.get("summary") == null) {
            throw new RuntimeException("Failed to get summary from Flask server");
        }

        return responseBody;
    }
}
