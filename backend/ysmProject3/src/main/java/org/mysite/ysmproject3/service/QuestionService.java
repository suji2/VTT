package org.mysite.ysmproject3.service;

import lombok.RequiredArgsConstructor;
import org.mysite.ysmproject3.domain.Member;
import org.mysite.ysmproject3.domain.Question;
import org.mysite.ysmproject3.domain.UserInfoEntity;
import org.mysite.ysmproject3.exception.DataNotFoundException;
import org.mysite.ysmproject3.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    //질문 등록
    public List<Question> createQuestion(String title, String content, String secretYN, UserInfoEntity userInfoEntity) {
        Question question = new Question();
        question.setTitle(title);
        question.setContent(content);
        question.setSecretYN(secretYN);
        question.setUserInfoEntity(userInfoEntity);

        this.questionRepository.save(question);

        return questionRepository.findAll();
    }

    //전체 질문 조회
    public List<Question> getAllQuestion(){
        return questionRepository.findAll();
    }

    //단일 질문 조회
    public Question getQuestion(Long id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("질문을 찾을 수 없음");
        }
    }
    // 질문 수정
    public Question updateQuestion(Long id, String title, String content, String secretYN) {
        Optional<Question> questionOptional = this.questionRepository.findById(id);
        if (questionOptional.isPresent()) {
            Question question = questionOptional.get();
            question.setTitle(title);
            question.setContent(content);
            question.setSecretYN(secretYN);

            this.questionRepository.save(question);
            return question;
        } else {
            throw new DataNotFoundException("질문을 찾을 수 없음");
        }
    }

    // 질문 삭제
    public void deleteQuestion(Long id) {
        Optional<Question> questionOptional = this.questionRepository.findById(id);
        if (questionOptional.isPresent()) {
            this.questionRepository.deleteById(id);
        } else {
            throw new DataNotFoundException("질문을 찾을 수 없음");
        }
    }
}
