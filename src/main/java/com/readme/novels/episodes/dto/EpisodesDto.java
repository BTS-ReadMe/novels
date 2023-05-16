package com.readme.novels.episodes.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
public class EpisodesDto {
    private Long id;
    private Long novelsId;
    private String title;
    private String content;
    private Long views;
    private Boolean free;
    private LocalDateTime registration;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String status;
}
