package com.readme.novels.messageQueue.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class GetPurchasedInfoDto {

    String id;
    List<Long> episodeIds;
}
