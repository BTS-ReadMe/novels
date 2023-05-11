package com.readme.novels.novels.dto;

import com.readme.novels.novels.model.Novels.Genre;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
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
    private Integer grade;
    private Genre genre;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private List<Tags> tags;

    @Getter
    public class Tags {
        private String name;
    }
}
