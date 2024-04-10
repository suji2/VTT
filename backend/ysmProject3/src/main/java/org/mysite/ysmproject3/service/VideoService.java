package org.mysite.ysmproject3.service;


import org.mysite.ysmproject3.domain.Video;
import org.mysite.ysmproject3.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    private final VideoRepository videoRepository;

    @Autowired
    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    //전체 비디오 조회
    public List<Video> getAllVideo() {
        return videoRepository.findAll();
    }

    //비디오 검색
    public List<Video> getSearchVideo(String title) {
        return videoRepository.findByTitleContaining(title);
    }
}