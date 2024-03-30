package org.mysite.ysmproject3.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "answer")
@Getter
@Setter
public class Answer {

    @Id
    @Column(name = "answer")
    private Long answer;

    @Column(name = "Field")
    private String field;

    @OneToOne
    private Question question;
    // getters and setters
}