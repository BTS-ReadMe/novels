package com.readme.novels.episodes.repository;

import com.readme.novels.episodes.model.Episodes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodesRepository extends JpaRepository<Episodes, Long> {

}
