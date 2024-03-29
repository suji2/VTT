package org.mysite.ysmproject3.repository;

import org.mysite.ysmproject3.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findByTitleContaining(String title);
}
