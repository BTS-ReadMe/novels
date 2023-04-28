package com.readme.novels.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Episodes {

    private Long novelId;
    private String title;
    private String content;
    private Long views;
    private Boolean free;

}
