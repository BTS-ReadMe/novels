package com.readme.novels.episodes.model;

import com.readme.novels.episodes.dto.EpisodesDto;
import com.readme.novels.utils.BaseTimeEntity;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Episodes extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long novelsId;
    private String title;
    private String content;
    private long views;
    private boolean free;
    private LocalDateTime registration;
    private String status;

    public Episodes(EpisodesDto episodesDto) {
        this.id = episodesDto.getId();
        this.novelsId = episodesDto.getNovelsId();
        this.title = episodesDto.getTitle();
        this.content = episodesDto.getContent();
        this.views = episodesDto.getViews();
        this.free = episodesDto.isFree();
        this.registration = episodesDto.getRegistration();
        this.status = episodesDto.getStatus();
    }

    public Episodes(EpisodesDto episodesDto, Episodes episodes) {
        super();
    }
}
