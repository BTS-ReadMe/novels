package com.readme.novels.novels.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class NovelsSearchParamDto {

    private String author;
    private String title;
    private Integer page;

}
