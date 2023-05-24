package com.readme.novels.novels.dto;

import com.readme.novels.novels.model.Novels;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class ResponseNovelsDto {

    private long id;
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
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private List<String> tags;

    public ResponseNovelsDto(Novels novels) {
        this.id = novels.getId();
        this.title = novels.getTitle();
        this.description = novels.getDescription();
        this.author = novels.getAuthor();
        this.startDate = novels.getStartDate();
        this.serializationStatus = novels.getSerializationStatus();
        this.thumbnail = novels.getThumbnail();
        this.authorComment = novels.getAuthorComment();
        this.grade = novels.getGrade();
        this.createDate = novels.getCreateDate();
        this.updateDate = novels.getUpdateDate();
    }

    public void setModifiedSerializationDay(List<String> serializationDay) {
        this.serializationDay = serializationDay;
    }

    public void setModifiedTags(List<String> tags) {
        this.tags = tags;
    }

    public void setGenreOfMainCategory(String genre) {
        this.genre = genre;
    }
}
