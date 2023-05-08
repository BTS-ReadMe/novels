package com.readme.novels.episodes.repository;

import com.readme.novels.episodes.model.Episodes;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodesRepository extends JpaRepository<Episodes, Long> {
    public Page<Episodes> findByNovelsIdOrderByCreateDateDesc(Long novelsId, Pageable pageable);
}
