package org.mysite.ysmproject3;

import org.junit.jupiter.api.Test;
import org.mysite.ysmproject3.youtube.YoutubeService;

public class TokenTest {
    private final YoutubeService youtubeService;

    public TokenTest(YoutubeService youtubeService) {
        this.youtubeService = youtubeService;
    }

    @Test
    public void getComment() {
        String accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNzE3NzQ3MTg4LCJleHAiOjE3MTc4MzM1ODgsInJvbGUiOiJST0xFX1VTRVIifQ.LPJeYuQKzBpX-A2EBYso6AG8GNB9VXQ0Xt7K8a2GA0XbefxknJfYdU0VFBG6bJxybuMsSl-Q5jlZ_8knTR7crQ";
        String videoId = "1ifSAFCGiX8";
        System.out.println(youtubeService.getComments(accessToken, videoId));
    }
}
