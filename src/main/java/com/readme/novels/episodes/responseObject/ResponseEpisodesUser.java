package com.readme.novels.episodes.responseObject;

import com.readme.novels.episodes.dto.EpisodesDtoByUser;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ResponseEpisodesUser {
    private long id;
    private String novelsTitle;
    private String title;
    private String content;
    private String registration;

    public ResponseEpisodesUser(EpisodesDtoByUser episodesDtoByUser) {
        this.id = episodesDtoByUser.getId();
        this.novelsTitle = episodesDtoByUser.getNovelsTitle();
        this.title = episodesDtoByUser.getTitle();
        this.content = episodesDtoByUser.getContent();
        this.registration = episodesDtoByUser.getRegistration();
    }
}
