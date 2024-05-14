package org.mysite.ysmproject3.domain;

import jakarta.persistence.*;
import lombok.Cleanup;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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

    @Column(name = "CREATION_DATE")
    private Date createDate;

    @Column(name = "ANSWER_Y_N")
    private String answerYN;

    @Column(name = "SECRET")
    private String secret;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

}
