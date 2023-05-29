package com.readme.novels.episodes.model;

import com.readme.novels.utils.BaseTimeEntity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class EpisodeHistory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private Long novelId;
    private Long episodeId;
    // private Long readAt; 읽은 위치? 페이지? 줄수? 나중에 추가


    public EpisodeHistory(String uuid, Long novelId, Long episodeId) {
        this.uuid = uuid;
        this.novelId = novelId;
        this.episodeId = episodeId;
    }
}
