package org.mysite.ysmproject3.oauth;

import lombok.extern.slf4j.Slf4j;
import org.mysite.ysmproject3.domain.UserInfoEntity;
import org.mysite.ysmproject3.dto.UserRequestDto;
import org.mysite.ysmproject3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PrincipalOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    // OAuth2User객체로 리턴하면 spring secuirty가 알아서 Session에 저장해줌
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);

        log.info("[userRequest.getAccessToken.getTokenValue()] : {}", userRequest.getAccessToken().getTokenValue());
        log.info("[userRequest.getClientRegistration()] : {}", userRequest.getClientRegistration());
        log.info("[oauth2User.getAttributes()] : {}", oauth2User.getAttributes());

        String provider = userRequest.getClientRegistration().getClientName(); // google
        String providerId = oauth2User.getAttribute("sub"); // google 클라이언트 아이디
        String email = oauth2User.getAttribute("email");
        String clientUserId = provider+"_"+providerId;

        String accessToken = userRequest.getAccessToken().getTokenValue();
        System.out.println(accessToken);

        UserRequestDto userRequestDto = null;

        userRequestDto = UserRequestDto.builder()
                .clientUserId(clientUserId)
                .userName(oauth2User.getAttribute("name"))
                .img(oauth2User.getAttribute("picture"))
                .email(email)
                .userAuthor("ROLE_USER")
                .accessToken(accessToken)
                .build();

        UserInfoEntity userInfoEntity = null;

        userInfoEntity = UserInfoEntity.builder()
                .clientUserId(clientUserId)
                .userName(oauth2User.getAttribute("name"))
                .img(oauth2User.getAttribute("picture"))
                .email(email)
                .userAuthor("ROLE_USER")
                .build();

        userRepository.save(userInfoEntity);

        return new PrincipalDetails(userRequestDto, oauth2User.getAttributes()); // Authentication 객체를 security session에 넣어줌

    }
}