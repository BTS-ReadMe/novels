package com.readme.novels.novels.controller;

import com.readme.novels.commonResponseObject.CommonDataResponse;
import com.readme.novels.novels.dto.NovelMainDto;
import com.readme.novels.novels.repository.NovelMainRepository;
import com.readme.novels.novels.responseObject.ResponseNovelMain;
import com.readme.novels.novels.service.NovelMainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/main")
@RequiredArgsConstructor
@Slf4j
public class NovelsController {

    private final NovelMainService novelMainService;

    @GetMapping
    public ResponseEntity<CommonDataResponse<ResponseNovelMain>> getMainNovel() {
        NovelMainDto novelMainDto = novelMainService.getMainNovel();

        return ResponseEntity.ok(new CommonDataResponse<>(new ResponseNovelMain(novelMainDto)));
    }
}
