package org.mysite.ysmproject3.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "comment_summary")
@Getter
@Setter
public class CommentSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "mySequence", sequenceName = "voice_seq", allocationSize = 1)
    @Column(name = "summary_num")
    private Long summaryNum;

    @OneToOne
    @JoinColumn(name = "video_id")
    private Video video;

    @Column(name = "comment")
    private String comment;

    @Column(name = "comment_summary")
    private String commentSummary;

}