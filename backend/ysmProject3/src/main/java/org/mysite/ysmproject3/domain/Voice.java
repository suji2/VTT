package org.mysite.ysmproject3.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "voice")
@Getter
@Setter
public class Voice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "mySequence", sequenceName = "voice_seq", allocationSize = 1)
    @Column(name = "voice_id")
    private Long voiceId;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private Video video;

    @Column(name = "conversion")
    private String conversion;

    @Column(name = "voice_Url")
    private String voiceUrl;

}