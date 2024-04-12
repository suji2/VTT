package org.mysite.ysmproject3.repository;

import org.mysite.ysmproject3.domain.Answer;
import org.mysite.ysmproject3.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> findByQuestion(Question id);
}
