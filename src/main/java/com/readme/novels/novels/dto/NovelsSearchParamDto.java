package com.readme.novels.novels.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NovelsSearchParamDto {

    private String author;
    private String title;
    private Integer page;

}
