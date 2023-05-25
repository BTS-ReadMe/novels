package com.readme.novels.episodes.dto;

import lombok.Getter;

@Getter
public class EpisodesDeleteKafkaDto {
    private long id;

    public EpisodesDeleteKafkaDto(long id) {
        this.id = id;
    }
}
