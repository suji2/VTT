package org.mysite.ysmproject3.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChannelDTO {
    // 채널 아이디
    private String channelId;
    // 채널 이름
    private String title;
    // 채널 설명
    private String description;
    // 채널 섬네일
    private String thumnailUrl;

}
