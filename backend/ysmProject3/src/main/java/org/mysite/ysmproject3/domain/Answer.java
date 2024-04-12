package org.mysite.ysmproject3.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "answer")
@Getter
@Setter
public class Answer {

    @Id
    @Column(name = "answer_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "mySequence", sequenceName = "answer_seq", allocationSize = 1)
    private Long answerId;

    @Column(name = "answer_text")
    private String text;

    @Column(name = "answer_date")
    private LocalDateTime answer_date;

    @Column(name = "modified_date")
    private LocalDateTime modified_date;

    @OneToOne
    @JoinColumn(name = "question_id")
    private Question question;

}