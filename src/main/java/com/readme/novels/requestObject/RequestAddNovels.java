package com.readme.novels.requestObject;

import com.readme.novels.model.Novels.Genre;
import com.readme.novels.model.Novels.Grade;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestAddNovels {

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
}
