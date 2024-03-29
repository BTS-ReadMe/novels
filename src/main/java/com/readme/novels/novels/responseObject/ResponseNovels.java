package com.readme.novels.novels.responseObject;

import java.util.Date;
import java.util.List;
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
    private List<String> serializationDay;
    private String thumbnail;
    private String serializationStatus;
    private String authorComment;

    private String genre;
    private Integer grade;

    private List<String> tags;
}
