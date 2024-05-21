package org.mysite.ysmproject3.service;

import lombok.RequiredArgsConstructor;
import org.mysite.ysmproject3.repository.AnswerRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    //답변 등록 -> 질문게시판의 답변 여부도 Y로 수정

    //답변 조회

}
