package com.readme.novels.controller;

import com.readme.novels.dto.NovelsDto;
import com.readme.novels.model.Novels;
import com.readme.novels.model.Novels.Genre;
import com.readme.novels.model.Novels.Grade;
import com.readme.novels.requestObject.RequestAddNovels;
import com.readme.novels.service.NovelsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/admin/novels")
@RequiredArgsConstructor
public class AdminNovelsController {

    private final NovelsService novelsService;
    private final Environment env;

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in User Service on PORT : %s"
            , env.getProperty("local.server.port"));
    }
    @PostMapping
    public void addNovels(@RequestBody RequestAddNovels requestAddNovels) {
        ModelMapper mapper = new ModelMapper();
        NovelsDto novelsDto = mapper.map(requestAddNovels, NovelsDto.class);

        novelsService.addNovels(novelsDto);

    }
}
