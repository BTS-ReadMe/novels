package com.readme.novels.episodes.repository;

import com.readme.novels.episodes.model.EpisodeHistory;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeHistoryRepository extends JpaRepository<EpisodeHistory, Long> {
    Optional<EpisodeHistory> findByUuidAndNovelId(String uuid, long novelId);

    Page<EpisodeHistory> findByUuidOrderByUpdateDateDesc(String uuid, Pageable pageable);

}
