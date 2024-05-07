package org.mysite.ysmproject3.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Video_Summary")
@Getter
@Setter
public class CaptionSummary {

    @Id
    @Column(name = "Summary_Num")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "mySequence", sequenceName = "member_seq", allocationSize = 1)
    private Long videoSummaryNum;

    @OneToOne
    @JoinColumn(name = "video_id")
    private Video video;

    @Column(name = "caption")
    private String caption;

    @Column(name = "caption_summary")
    private String captionSummary;

    // getters and setters
}