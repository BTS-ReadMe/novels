package com.readme.novels.controller;

import com.readme.novels.dto.NovelsDto;
import com.readme.novels.model.Novels;
import com.readme.novels.model.Novels.Genre;
import com.readme.novels.model.Novels.Grade;
import com.readme.novels.requestObject.RequestAddNovels;
import com.readme.novels.requestObject.RequestUpdateNovels;
import com.readme.novels.service.NovelsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/admin/novels")
@RequiredArgsConstructor
@Slf4j
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

    @PutMapping("/{novels_id}")
    public void updateNovels(@RequestBody RequestUpdateNovels requestUpdateNovels, @PathVariable Long novels_id){
        ModelMapper mapper = new ModelMapper();
        NovelsDto novelsDto = mapper.map(requestUpdateNovels, NovelsDto.class);
        novelsDto.setId(novels_id);

        log.info(novelsDto.toString());

        novelsService.updateNovels(novelsDto);

    }

}
