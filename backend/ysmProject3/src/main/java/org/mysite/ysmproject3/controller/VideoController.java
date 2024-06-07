package org.mysite.ysmproject3.controller;

import lombok.RequiredArgsConstructor;
import org.mysite.ysmproject3.domain.Video;
import org.mysite.ysmproject3.service.VideoService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    //전체 비디오 조회
    @GetMapping("/videos")
    @ResponseBody
    public List<Video> video() {
        List<Video> result = videoService.getAllVideo();
        System.out.println(result);
        return result;
    }

    //비디오 검색
    @GetMapping("/videos/search")
    @ResponseBody
    public List<Video> searchVideo(@RequestParam(required = true) String title) {
        return videoService.getSearchVideo(title);
    }


}
