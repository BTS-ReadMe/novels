package com.readme.novels.episodes.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class EpisodesDeleteKafkaDto {
    private long id;

    public EpisodesDeleteKafkaDto(long id) {
        this.id = id;
    }
}
