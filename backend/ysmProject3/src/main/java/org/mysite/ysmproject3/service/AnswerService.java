package org.mysite.ysmproject3.service;

import org.mysite.ysmproject3.domain.Answer;
import org.mysite.ysmproject3.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    @Autowired
    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    //답변 조회
    public Optional<Answer> getAnswer(Long question_id) {
        return answerRepository.findById(question_id);
    }
}
