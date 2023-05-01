package com.readme.novels.controller;

import com.readme.novels.model.Episodes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/novels-service")
public class EpisodesController {

    @GetMapping("/episodes")
    public Episodes getEpisode() {
        return Episodes.builder()
            .novelId(1L)
            .title("hello_1")
            .content("hello!")
            .views(100L)
            .free(Boolean.TRUE)
            .build();
    }


}
