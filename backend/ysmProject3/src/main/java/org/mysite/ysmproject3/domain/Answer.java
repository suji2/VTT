package org.mysite.ysmproject3.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "ANSWER")
@Getter
@Setter
public class Answer {

    @Id
    @Column(name = "ANSWER_NUM")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "mySequence", sequenceName = "answer_seq", allocationSize = 1)
    private Long answer_num;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @CreationTimestamp
    @Column(name = "ANSWER_DATE")
    private LocalDateTime answerDate;

    @OneToOne
    @JoinColumn(name = "CLIENT_USER_ID")
    private UserInfoEntity userInfoEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "QUESTION_NUM")
    private Question question;


}
