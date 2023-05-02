package com.readme.novels.repository;

import com.readme.novels.model.Novels;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NovelsRepository extends JpaRepository<Novels, Long> {

}
