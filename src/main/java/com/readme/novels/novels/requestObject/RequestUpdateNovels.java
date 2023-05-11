package com.readme.novels.novels.requestObject;

import com.readme.novels.novels.dto.NovelsDto.Tags;
import com.readme.novels.novels.model.Novels.Genre;
import java.util.Date;
import java.util.List;
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
    private List<String> serializationDay;
    private String serializationStatus;
    private String thumbnail;
    private String authorComment;
    private Integer grade;
    private Genre genre;
    private List<Tags> tags;
}
