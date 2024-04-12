package org.mysite.ysmproject3.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Answer_Comment")
public class AnswerComment {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "mySequence", sequenceName = "questioncomment_seq", allocationSize = 1)
    private Long commentId;

    @Column(name = "text")
    private String text;

    @Column(name = "manager")
    private boolean manager;


}
