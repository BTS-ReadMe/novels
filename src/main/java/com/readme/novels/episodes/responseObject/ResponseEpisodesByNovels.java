package com.readme.novels.episodes.responseObject;

import java.time.LocalDateTime;
import lombok.Getter;

// 현재는 사용 x
@Getter
public class ResponseEpisodesByNovels {
    private Long id;
    private String title;
    private LocalDateTime registration;
    private boolean free;
    private String status;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
