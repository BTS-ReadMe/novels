package com.readme.novels.novels.requestObject;

import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
public class RequestUpdateNovels {

    private String title;
    private String description;
    private String author;
    private Date startDate;
    private List<String> serializationDay;
    private String serializationStatus;
    private String thumbnail;
    private String authorComment;
    private Integer grade;
    private String genre;
    private List<String> tags;
}
