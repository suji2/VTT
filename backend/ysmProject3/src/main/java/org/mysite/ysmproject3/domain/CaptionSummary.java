package org.mysite.ysmproject3.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "VIDEO_SUMMARY")
@Getter
@Setter
public class CaptionSummary {

    @Id
    @Column(name = "SUMMARY_NUM")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "mySequence", sequenceName = "member_seq", allocationSize = 1)
    private Long videoSummaryNum;

    @Column(name = "CAPTION")
    private String caption;

    @Column(name = "CAPTION_SUMMARY")
    private String captionSummary;

    @OneToOne
    @JoinColumn(name = "VIDEO_ID")
    private Video video;

}