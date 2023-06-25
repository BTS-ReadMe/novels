package com.readme.novels.messageQueue.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmojiStatusDto {

    private long episodeId;
    private int episodeRow;
    private int emoji;
    private int totalUser;
}
