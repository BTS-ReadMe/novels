package com.readme.novels.episodes.requestObject;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestUpdateEpisodes {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime registration;
    private Boolean free;
    private String status;
}
