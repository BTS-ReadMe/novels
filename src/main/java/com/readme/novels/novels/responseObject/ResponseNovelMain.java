package com.readme.novels.novels.responseObject;

import com.readme.novels.novels.dto.NovelMainDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ResponseNovelMain {
    private long id;
    private String mainImage;

    public ResponseNovelMain(NovelMainDto novelMainDto) {
        this.id = novelMainDto.getId();
        this.mainImage = novelMainDto.getMainImage();
    }
}
