package org.mysite.ysmproject3.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class VideoDTO {
    // 비디오 아이디
    private String videoId;
    // 채널 아이디
    private String channelId;
    // 비디오 제목
    private String title;
    // 비디오 설명
    private String description;
    // 게시일
    private String publishedAt;
    // 작은 섬네일    
    private String smThumbnail;
    // 큰 섬네일
    private String bigThumbnail;

}
