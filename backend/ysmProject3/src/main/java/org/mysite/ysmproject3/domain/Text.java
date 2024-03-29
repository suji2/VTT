package org.mysite.ysmproject3.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "text")
@Getter
@Setter
public class Text {

    @Id
    @Column(name = "text_Num")
    @SequenceGenerator(name = "mySequence", sequenceName = "member_seq", allocationSize = 1)
    private Integer textNum;

    @ManyToOne
    @JoinColumn(name = "voice_id")
    private Voice voice;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private Video video;

    @Column(name = "text_Summary")
    private String textSummary;

    // getters and setters
}