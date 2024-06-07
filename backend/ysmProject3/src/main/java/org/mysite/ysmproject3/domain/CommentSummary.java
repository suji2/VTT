package org.mysite.ysmproject3.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "COMMENT_SUMMARY")
@Getter
@Setter
public class CommentSummary {

    @Id
    @Column(name = "SUMMARY_NUM")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "mySequence", sequenceName = "voice_seq", allocationSize = 1)
    private Long summaryNum;

    @Column(name = "COMMENT_LIST")
    private String commentList;

    @Column(name = "COMMENT_SUMMARY")
    private String commentSummary;

    @OneToOne
    @JoinColumn(name = "VIDEO_ID")
    private Video video;

}