package org.mysite.ysmproject3.service;

import lombok.RequiredArgsConstructor;
import org.mysite.ysmproject3.domain.CaptionSummary;
import org.mysite.ysmproject3.repository.CaptionSummaryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CaptionSummaryService {
    private final CaptionSummaryRepository textRepository;

    //유튜브 자막 추출
    public void extractText(String videoId){
        /*
        유튜브 영상 자막 추출후 저장하는 로직 구현
        */
    }

    // DB에 저장된 자막 요약 가져오기
    public Optional<CaptionSummary> getSummary(String videoId) {
        return this.textRepository.findByVideoId(videoId);
    }

    // 자막 요약 하기
    // 저장된 자막을 짧게 요약하는 메소드
    public void Summary(String videoId) {
        /*
        요약 파이썬 스크립트 실행후 저장
        */
    }

}
