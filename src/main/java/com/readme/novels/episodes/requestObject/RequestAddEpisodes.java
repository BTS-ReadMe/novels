package com.readme.novels.episodes.requestObject;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class RequestAddEpisodes {

    private long novelsId;
    private String title;
    private String content;
    private boolean free;
    private LocalDateTime registration;
    private String status;
    private Long views;
}
