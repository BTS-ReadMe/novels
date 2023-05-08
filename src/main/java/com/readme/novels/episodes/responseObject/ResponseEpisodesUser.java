package com.readme.novels.episodes.responseObject;

import java.time.LocalDateTime;
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
public class ResponseEpisodesUser {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime registration;
}
