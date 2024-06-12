package org.mysite.ysmproject3.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    @SequenceGenerator(name = "user_seq_gen", sequenceName = "user_seq", allocationSize = 1)
    @Column(name = "user_id")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @Enumerated(EnumType.STRING) // Enum 타입은 문자열 형태로 저장해야 함
    @NotNull
    private Role role;

}