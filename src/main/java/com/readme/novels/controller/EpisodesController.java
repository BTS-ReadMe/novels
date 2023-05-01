package com.readme.novels.controller;

import com.readme.novels.model.Episodes;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/novels-service")
@RequiredArgsConstructor
public class EpisodesController {

    private final Environment env;
    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in User Service on PORT : %s"
            , env.getProperty("local.server.port"));
    }

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
