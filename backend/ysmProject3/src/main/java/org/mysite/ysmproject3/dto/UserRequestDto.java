package org.mysite.ysmproject3.dto;

import lombok.*;

// 사용자 관련 dto
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {

    // POST, PUT 요청 DTO
    // GET, DELETE는 인자로 전달해도 충분

 // 프로필 수정
    private String clientUserId;    // 이미지
    private String userName;        // 이름
    private String img;             // 이미지
    private String email;          // 이메일
    private String accessToken;     // 액세스 토큰
    private String userAuthor;


}