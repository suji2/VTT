package org.mysite.ysmproject3.oauth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.mysite.ysmproject3.dto.UserRequestDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class PrincipalDetails implements OAuth2User {

    private UserRequestDto userRequestDto;
    private Map<String, Object> attributes;

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ROLE_USER";
            }
        });
        return collection;
    }

    @Override
    public String getName() {
        return userRequestDto.getUserName();
    }

    
    public String getAccessToken() {
        return userRequestDto.getAccessToken();
    }

}