package org.mysite.ysmproject3.repository;

import org.mysite.ysmproject3.domain.CommentSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentSummaryRepository extends JpaRepository<CommentSummary, Long> {
}
