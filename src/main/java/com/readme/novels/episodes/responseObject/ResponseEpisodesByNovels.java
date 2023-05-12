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
public class ResponseEpisodesByNovels {
    private Long id;
    private String title;
    private LocalDateTime registration;
    private boolean free;
    private String status;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
