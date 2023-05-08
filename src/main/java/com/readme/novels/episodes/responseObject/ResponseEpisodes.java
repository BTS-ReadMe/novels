package com.readme.novels.episodes.responseObject;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseEpisodes {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime registration;
    private boolean free;
    private String status;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
