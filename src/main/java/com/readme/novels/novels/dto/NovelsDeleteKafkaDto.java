package com.readme.novels.novels.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class NovelsDeleteKafkaDto {
    private long id;

    public NovelsDeleteKafkaDto(long id) {
        this.id = id;
    }
}
