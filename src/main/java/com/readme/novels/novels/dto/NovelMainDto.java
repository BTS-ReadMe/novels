package com.readme.novels.novels.dto;

import com.readme.novels.novels.model.NovelMain;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class NovelMainDto {
    private long id;
    private String mainImage;

    public NovelMainDto(NovelMain novelMain) {
        this.id = novelMain.getId();
        this.mainImage = novelMain.getMainImage();
    }
}
