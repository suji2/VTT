package org.mysite.ysmproject3;

import org.junit.jupiter.api.Test;
import org.mysite.ysmproject3.domain.Member;
import org.mysite.ysmproject3.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InsertTests {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void JpaTest() {
        Member m1 = new Member();
        m1.setName("이승한");
        m1.setAge(26);
        this.memberRepository.save(m1);

        Member m2 = new Member();
        m2.setName("홍길동");
        m2.setAge(30);
        this.memberRepository.save(m2);

    }

}
