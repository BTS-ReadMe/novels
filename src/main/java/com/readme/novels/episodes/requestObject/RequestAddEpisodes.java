package com.readme.novels.episodes.requestObject;

import com.readme.novels.utils.BaseTimeEntity;
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
public class RequestAddEpisodes extends BaseTimeEntity {
    private Long novelsId;
    private String title;
    private String content;
    private Boolean free;
    private LocalDateTime registration;
    private String status;
}
