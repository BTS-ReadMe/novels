package com.readme.novels.episodes.responseObject;

import com.readme.novels.episodes.dto.EpisodesDtoByUser;
import com.readme.novels.episodes.responseObject.ResponseEpisodesPagination.Pagination;
import java.util.List;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Getter
@ToString
public class ResponseEpisodesUser {
    private long id;
    private long novelId;
    private String novelsTitle;
    private String title;
    private String content;
    private String registration;

    private long prevId;
    private boolean prevFree;
    private boolean prevRead;
    private long nextId;
    private boolean nextFree;
    private boolean nextRead;

    private SseEmitter emitter;

    public ResponseEpisodesUser(EpisodesDtoByUser episodesDtoByUser) {
        this.id = episodesDtoByUser.getId();
        this.novelId = episodesDtoByUser.getNovelsId();
        this.novelsTitle = episodesDtoByUser.getNovelsTitle();
        this.title = episodesDtoByUser.getTitle();
        this.content = episodesDtoByUser.getContent();
        this.registration = episodesDtoByUser.getRegistration();
        this.prevId = episodesDtoByUser.getPrevId();
        this.prevFree = episodesDtoByUser.isPrevFree();
        this.prevRead = episodesDtoByUser.isPrevRead();
        this.nextId = episodesDtoByUser.getNextId();
        this.nextFree = episodesDtoByUser.isNextFree();
        this.nextRead = episodesDtoByUser.isNextRead();
        this.emitter = episodesDtoByUser.getEmitter();
    }
}
