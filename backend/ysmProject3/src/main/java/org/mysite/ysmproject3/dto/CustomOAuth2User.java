package org.mysite.ysmproject3.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {

    private final Map<String, Object> attributes;
    private final String accessToken;

    public CustomOAuth2User(Map<String, Object> attributes, String accessToken) {
        this.attributes = attributes;
        this.accessToken = accessToken;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;  // 필요에 따라 구현
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    public String getEmail() {
        return (String) attributes.get("email");
    }

    public String getAccessToken() {
        return accessToken;
    }
}
