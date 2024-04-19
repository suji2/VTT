package org.mysite.ysmproject3.service;

import lombok.RequiredArgsConstructor;
import org.mysite.ysmproject3.domain.Member;
import org.mysite.ysmproject3.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberService implements OAuth2UserService<OidcUserRequest, OidcUser> {

    @Autowired
    private MemberRepository memberRepository;  // DB 접근을 위한 레포지토리

    public Member processUserRegistration(String email, String name, String picture) {
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            member = new Member();
            member.setEmail(email);
            member.setName(name);
            member.setPicture(picture);
            memberRepository.save(member);
        }
        return member;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcIdToken idToken = userRequest.getIdToken();
        OidcUserInfo userInfo = new OidcUserInfo(Map.of(
                "name", idToken.getClaimAsString("name"),
                "email", idToken.getClaimAsString("email"),
                "picture", idToken.getClaimAsString("picture")
        ));

        // DB에 사용자 정보 저장 또는 업데이트
        Member member = new Member();
        member.setName(userInfo.getFullName());
        member.setEmail(userInfo.getEmail());
        member.setPicture(userInfo.getPicture());
        memberRepository.save(member);

        return new DefaultOidcUser(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")), idToken, userInfo);
    }
}

