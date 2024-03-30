package org.mysite.ysmproject3.repository;

import jakarta.persistence.Id;
import org.mysite.ysmproject3.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
