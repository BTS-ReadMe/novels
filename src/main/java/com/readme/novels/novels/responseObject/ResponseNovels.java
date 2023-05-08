package com.readme.novels.novels.responseObject;

import com.readme.novels.novels.model.Novels.Genre;
import com.readme.novels.novels.model.Novels.Grade;
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
public class ResponseNovels {

    private Long id;
    private String title;
    private String author;
    private String description;
    private Date startDate;
    private Integer serializationDay;
    private String thumbnail;
    private String serializationStatus;
    private String authorComment;

    private Genre genre;
    private Grade grade;
}
