package org.mysite.ysmproject3.service;

import lombok.RequiredArgsConstructor;
import org.mysite.ysmproject3.domain.Answer;
import org.mysite.ysmproject3.domain.Question;
import org.mysite.ysmproject3.repository.AnswerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    public Optional<Answer> getAnswer(Long id) {
        return answerRepository.findById(id);
    }

    public void create(Question question, String text) {
        Answer answer = new Answer();
        answer.setText(text);
        answer.setAnswer_date(LocalDateTime.now());
        answer.setQuestion(question);
        this.answerRepository.save(answer);
    }

}
