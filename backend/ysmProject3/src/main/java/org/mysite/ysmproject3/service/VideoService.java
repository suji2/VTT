package org.mysite.ysmproject3.service;


import lombok.RequiredArgsConstructor;
import org.mysite.ysmproject3.domain.Video;
import org.mysite.ysmproject3.repository.VideoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;

    //전체 비디오 조회
    public List<Video> getAllVideo() {
        return videoRepository.findAll();
    }

    //비디오 검색
    public List<Video> getSearchVideo(String title) {
        return videoRepository.findByTitleContaining(title);
    }


}
