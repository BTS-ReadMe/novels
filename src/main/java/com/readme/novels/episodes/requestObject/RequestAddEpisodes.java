package com.readme.novels.episodes.requestObject;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class RequestAddEpisodes {

    private Long novelsId;
    private String title;
    private String content;
    private Boolean free;
    private LocalDateTime registration;
    private String status;
    private Long views;
}
