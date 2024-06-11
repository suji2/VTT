package org.mysite.ysmproject3.service;

import lombok.RequiredArgsConstructor;
import org.mysite.ysmproject3.domain.Answer;
import org.mysite.ysmproject3.domain.Member;
import org.mysite.ysmproject3.domain.Question;
import org.mysite.ysmproject3.domain.UserInfoEntity;
import org.mysite.ysmproject3.exception.DataNotFoundException;
import org.mysite.ysmproject3.repository.AnswerRepository;
import org.mysite.ysmproject3.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    // 답변 등록 -> 질문게시판의 답변 여부도 Y로 수정
    public Answer createAnswer(String title, String content, UserInfoEntity userInfoEntity, Long questionNum) {
        Question question = questionRepository.findById(questionNum)
                .orElseThrow(() -> new DataNotFoundException("질문을 찾을 수 없음"));

        // 새로운 답변 생성
        Answer answer = new Answer();
        answer.setTitle(title);
        answer.setContent(content);
        answer.setUserInfoEntity(userInfoEntity);
        answer.setQuestion(question);

        // 답변 저장
        Answer savedAnswer = answerRepository.save(answer);

        // 질문의 ANSWER_Y_N 필드를 'Y'로 업데이트
        question.setAnswerYN("Y");
        questionRepository.save(question);

        return savedAnswer;
    }

    // 답변 조회
    public List<Answer> getAnswersByQuestionNum(Long questionNum) {
        Question question = questionRepository.findById(questionNum)
                .orElseThrow(() -> new DataNotFoundException("질문을 찾을 수 없음"));

        return answerRepository.findByQuestion(question);
    }
}
