package com.readme.novels.episodes.dto;

import com.readme.novels.episodes.requestObject.RequestUpdateReadAt;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UpdateReadAtDto {
    private long readAt;
    private long episodeId;
    private String uuid;

    public UpdateReadAtDto(String uuid, RequestUpdateReadAt requestUpdateReadAt) {
        this.readAt = requestUpdateReadAt.getReadAt();
        this.episodeId = requestUpdateReadAt.getEpisodeId();
        this.uuid = uuid;
    }
}
