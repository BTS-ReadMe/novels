package com.readme.novels.episodes.repository;

import com.readme.novels.episodes.model.EpisodeHistory;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeHistoryRepository extends JpaRepository<EpisodeHistory, Long> {
    Optional<EpisodeHistory> findByUuidAndNovelId(String uuid, long novelId);

}
