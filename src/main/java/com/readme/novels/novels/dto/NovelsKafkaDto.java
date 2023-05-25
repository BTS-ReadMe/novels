package com.readme.novels.novels.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import lombok.Getter;

@Getter
public class NovelsKafkaDto {

    private Long id;
    private String title;
    private String description;
    private String author;
    private Date startDate;
    private List<String> serializationDay;
    private String serializationStatus;
    private String thumbnail;
    private String authorComment;
    private int grade;
    private String genre;
    private List<String> tags;

    public NovelsKafkaDto(NovelsDto novelsDto) {
        this.id = novelsDto.getId();
        this.title = novelsDto.getTitle();
        this.description = novelsDto.getDescription();
        this.author = novelsDto.getAuthor();
        this.startDate = novelsDto.getStartDate();
        this.serializationDay = novelsDto.getSerializationDay();
        this.serializationStatus = novelsDto.getSerializationStatus();
        this.thumbnail = novelsDto.getThumbnail();
        this.authorComment = novelsDto.getAuthorComment();
        this.grade = novelsDto.getGrade();
        this.genre = novelsDto.getGenre();
        this.tags = novelsDto.getTags();
    }
}
