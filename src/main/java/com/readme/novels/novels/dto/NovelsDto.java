package com.readme.novels.novels.dto;

import com.readme.novels.novels.requestObject.RequestAddNovels;
import com.readme.novels.novels.requestObject.RequestUpdateNovels;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class NovelsDto {

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
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private List<String> tags;

    public NovelsDto(RequestAddNovels requestAddNovels) {
        this.title = requestAddNovels.getTitle();
        this.description = requestAddNovels.getDescription();
        this.author = requestAddNovels.getAuthor();
        this.startDate = requestAddNovels.getStartDate();
        this.serializationDay = requestAddNovels.getSerializationDay();
        this.serializationStatus = requestAddNovels.getSerializationStatus();
        this.thumbnail = requestAddNovels.getThumbnail();
        this.authorComment = requestAddNovels.getAuthorComment();
        this.grade = requestAddNovels.getGrade();
        this.genre = requestAddNovels.getGenre();
        this.tags = requestAddNovels.getTags();
    }

    public NovelsDto(RequestUpdateNovels requestUpdateNovels, Long id) {
        this.id = id;
        this.title = requestUpdateNovels.getTitle();
        this.description = requestUpdateNovels.getDescription();
        this.author = requestUpdateNovels.getAuthor();
        this.startDate = requestUpdateNovels.getStartDate();
        this.serializationDay = requestUpdateNovels.getSerializationDay();
        this.serializationStatus = requestUpdateNovels.getSerializationStatus();
        this.thumbnail = requestUpdateNovels.getThumbnail();
        this.authorComment = requestUpdateNovels.getAuthorComment();
        this.grade = requestUpdateNovels.getGrade();
        this.genre = requestUpdateNovels.getGenre();
        this.tags = requestUpdateNovels.getTags();
    }

    public void setCreatedNovelId(Long id) {
        this.id = id;
    }
}
