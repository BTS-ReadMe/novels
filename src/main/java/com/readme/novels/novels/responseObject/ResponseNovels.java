package com.readme.novels.novels.responseObject;

import com.readme.novels.novels.dto.ResponseNovelsDto;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
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

    public ResponseNovels(ResponseNovelsDto responseNovelsDto) {
        this.id = responseNovelsDto.getId();
        this.title = responseNovelsDto.getTitle();
        this.author = responseNovelsDto.getAuthor();
        this.description = responseNovelsDto.getDescription();
        this.startDate = responseNovelsDto.getStartDate();
        this.serializationDay = responseNovelsDto.getSerializationDay();
        this.thumbnail = responseNovelsDto.getThumbnail();
        this.serializationStatus = responseNovelsDto.getSerializationStatus();
        this.authorComment = responseNovelsDto.getAuthorComment();
        this.genre = responseNovelsDto.getGenre();
        this.grade = responseNovelsDto.getGrade();
        this.tags = responseNovelsDto.getTags();
    }
}
