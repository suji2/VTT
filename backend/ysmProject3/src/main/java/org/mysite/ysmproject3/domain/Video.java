package org.mysite.ysmproject3.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VIDEO_ID")
    private Long id;

    @Column(name = "VIDEO_URL")
    private String url;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CHANNEL_TITLE")
    private String channel;

    @Column(name = "PUBLISHED_AT")
    private String date;

    @Column(name = "THUMBNAIL")
    private String thumbnail;
}
