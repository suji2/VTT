package org.mysite.ysmproject3.repository;

import org.mysite.ysmproject3.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
