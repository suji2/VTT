package org.mysite.ysmproject3.repository;

import org.mysite.ysmproject3.domain.CaptionSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CaptionSummaryRepository extends JpaRepository<CaptionSummary, Long> {
    Optional<CaptionSummary> findByVideoId(String videoId);
}
