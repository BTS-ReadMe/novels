package com.readme.novels.episodes.repository;

import com.readme.novels.episodes.dto.EpisodeRecentHistoryDto;
import com.readme.novels.episodes.model.EpisodeHistory;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EpisodeHistoryRepository extends JpaRepository<EpisodeHistory, Long> {
    List<EpisodeHistory> findByUuidAndNovelId(String uuid, long novelId);

    Page<EpisodeHistory> findByUuidOrderByUpdateDateDesc(String uuid, Pageable pageable);

    Boolean existsByIdAndUuid(Long id, String uuid);

    Optional<EpisodeHistory> findByUuidAndEpisodeId(String uuid, long episodeId);

    @Query("SELECT new com.readme.novels.episodes.dto.EpisodeRecentHistoryDto(e.novelId, MAX(e.episodeId)) " +
        "FROM EpisodeHistory e " +
        "WHERE e.uuid = :uuid " +
        "GROUP BY e.novelId")
    Page<EpisodeRecentHistoryDto> findRecentEpisodeIdsByUuid(@Param("uuid") String uuid, Pageable pageable);

    Boolean existsByUuidAndEpisodeId(String uuid, Long episode);
}
