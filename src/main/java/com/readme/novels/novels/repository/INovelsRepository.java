package com.readme.novels.novels.repository;

import com.readme.novels.novels.model.Novels;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INovelsRepository extends JpaRepository<Novels, Long> {

}
