package org.mysite.ysmproject3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class SelectTests {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void SelectTests() {
        Optional<Member> S1 = this.memberRepository.findById(1);
        System.out.println(S1.get().getName());

        Optional<Member> S2 = this.memberRepository.findById(2);
        System.out.println(S2.get().getName());
    }

}
