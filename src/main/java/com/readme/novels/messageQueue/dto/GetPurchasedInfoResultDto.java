package com.readme.novels.messageQueue.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetPurchasedInfoResultDto {

    private String id;
    private List<EpisodeNovelDto> purchased;
}
