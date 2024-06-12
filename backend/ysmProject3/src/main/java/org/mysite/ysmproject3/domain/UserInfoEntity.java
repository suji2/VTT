package org.mysite.ysmproject3.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "MBER_INFO")
@Getter
@Setter
@Builder
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoEntity {

    @Id
    @Column(name="CLIENT_USER_ID")
    private String clientUserId; // 클라이언트아이디

    @Column(name="NM", nullable = false)
    private String userName; // 이름

    @Column(name = "ATCH_PHOTO", nullable = false)
    private String img; // 이미지

    @Column(name = "EMAIL", nullable = false)
    private String email; // 이메일

    @Column(name = "SRVMNGH") // default "ROLE_USER"
    private String userAuthor; // 서비스관리권한


}