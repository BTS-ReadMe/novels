package com.readme.novels.novels.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class NovelsDeleteKafkaDto {
    private long novelId;

    public NovelsDeleteKafkaDto(long id) {
        this.novelId = id;
    }
}
