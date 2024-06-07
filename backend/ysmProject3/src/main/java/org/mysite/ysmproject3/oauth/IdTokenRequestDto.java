package org.mysite.ysmproject3.oauth;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IdTokenRequestDto {

    @NotNull(message = "Token cannot be null")
    private String idToken;
}
