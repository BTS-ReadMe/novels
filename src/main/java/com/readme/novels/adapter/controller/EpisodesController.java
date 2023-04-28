package com.readme.novels.adapter.controller;

import com.readme.novels.domain.model.Episodes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/episodes-service")
public class EpisodesController {

    @GetMapping
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
