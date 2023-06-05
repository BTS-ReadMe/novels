package com.readme.novels.episodes.responseObject;

import com.readme.novels.episodes.dto.EpisodesDtoByUser;
import com.readme.novels.episodes.responseObject.ResponseEpisodesPagination.Pagination;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ResponseEpisodesUser {
    private long id;
    private long novelId;
    private String novelsTitle;
    private String title;
    private List<String> content;
    private String registration;
    private Pagination pagination;

    public ResponseEpisodesUser(EpisodesDtoByUser episodesDtoByUser) {
        this.id = episodesDtoByUser.getId();
        this.novelId = episodesDtoByUser.getNovelsId();
        this.novelsTitle = episodesDtoByUser.getNovelsTitle();
        this.title = episodesDtoByUser.getTitle();
        this.content = episodesDtoByUser.getContent();
        this.registration = episodesDtoByUser.getRegistration();
        this.pagination = episodesDtoByUser.getPagination();
    }
}
