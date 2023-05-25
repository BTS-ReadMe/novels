package com.readme.novels.novels.dto;

import lombok.Getter;

@Getter
public class NovelsDeleteKafkaDto {
    private long id;

    public NovelsDeleteKafkaDto(long id) {
        this.id = id;
    }
}
