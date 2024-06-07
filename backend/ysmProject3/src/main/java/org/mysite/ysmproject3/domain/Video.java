package org.mysite.ysmproject3.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "VIDEO")
@Getter
@Setter
public class Video {

    @Id
    @Column(name = "VIDEO_ID")
    private String id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CHANNEL_TITLE")
    private String channel;

    @Column(name = "PUBLISHED_AT")
    private String publishedAt;

    @Column(name = "SM_THUMBNAIL")
    private String smThumbnail;

    @Column(name = "BIG_THUMBNAIL")
    private String bigThumbnail;
}
