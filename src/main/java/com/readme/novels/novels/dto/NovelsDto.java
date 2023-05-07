package com.readme.novels.novels.dto;

import com.readme.novels.novels.model.Novels.Genre;
import com.readme.novels.novels.model.Novels.Grade;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NovelsDto {

    private Long id;
    private String title;
    private String description;
    private String author;
    private Date startDate;
    private Integer serializationDay;
    private String serializationStatus;
    private String thumbnail;
    private String authorComment;
    private Grade grade;
    private Genre genre;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
