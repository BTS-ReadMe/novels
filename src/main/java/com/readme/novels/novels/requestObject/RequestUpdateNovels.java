package com.readme.novels.novels.requestObject;

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
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateNovels {

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
}
