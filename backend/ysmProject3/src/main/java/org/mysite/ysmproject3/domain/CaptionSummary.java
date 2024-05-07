package org.mysite.ysmproject3.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Video_Summary")
@Getter
@Setter
public class VideoSummary {

    @Id
    @Column(name = "Video_Summary_Num")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "mySequence", sequenceName = "member_seq", allocationSize = 1)
    private Long videoSummaryNum;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private Video video;

    @Column(name = "text_Summary")
    private String textSummary;

    // getters and setters
}