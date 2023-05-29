package com.readme.novels.episodes.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class EpisodesDeleteKafkaDto {
    private long episodeId;
    private long novelId;

    public EpisodesDeleteKafkaDto(long id, long novelId) {
        this.episodeId = id;
        this.novelId = novelId;
    }
}
