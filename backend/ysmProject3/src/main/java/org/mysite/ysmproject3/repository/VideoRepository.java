package org.mysite.ysmproject3.repository;

import org.mysite.ysmproject3.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, String> {
    List<Video> findByTitleContaining(String title);
    Optional<Video> findById(String id); // 올바른 ID 타입과 메서드 명 사용
}
