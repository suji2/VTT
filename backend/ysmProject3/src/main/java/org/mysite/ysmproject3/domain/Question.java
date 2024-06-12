package org.mysite.ysmproject3.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Entity
@Table(name = "question")
@Getter
@Setter
public class Question {

    @Id
    @Column(name = "QUESTION_NUM")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "mySequence", sequenceName = "question_seq", allocationSize = 1)
    private Long question_num;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @CreationTimestamp
    @Column(name = "CREATION_DATE")
    private LocalDateTime createDate;

    @Column(name = "ANSWER_Y_N")
    @ColumnDefault("N")
    private String answerYN;

    @Column(name = "SECRET_Y_N")
    private String secretYN;

    @ManyToOne
    @JoinColumn(name = "CLIENT_USER_ID")
    private UserInfoEntity userInfoEntity;

}
