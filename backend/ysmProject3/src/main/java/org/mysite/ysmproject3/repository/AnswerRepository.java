package org.mysite.ysmproject3.repository;

import org.mysite.ysmproject3.domain.Answer;
import org.mysite.ysmproject3.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestion(Question question);
}
