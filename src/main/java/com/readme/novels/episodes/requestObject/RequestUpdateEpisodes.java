package com.readme.novels.episodes.requestObject;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class RequestUpdateEpisodes {
    private long novelsId;
    private String title;
    private String content;
    private LocalDateTime registration;
    private boolean free;
    private String status;
}
