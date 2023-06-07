package com.readme.novels.novels.repository;

import com.readme.novels.messageQueue.dto.EpisodeNovelDto;
import com.readme.novels.novels.model.Novels;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface INovelsRepository extends JpaRepository<Novels, Long> {

    @Query("SELECT new com.readme.novels.messageQueue.dto.EpisodeNovelDto(e.title, n.title, n.id, n.grade, n.thumbnail) " +
        "FROM Episodes e JOIN Novels n ON e.novelsId = n.id WHERE e.id IN :ids")
    List<EpisodeNovelDto> findEpisodeNovelInfoByIds(@Param("ids") List<Long> ids);


}