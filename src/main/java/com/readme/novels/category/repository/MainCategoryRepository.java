package com.readme.novels.category.repository;

import com.readme.novels.category.model.MainCategory;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainCategoryRepository extends JpaRepository<MainCategory, Long> {
    List<MainCategory> findAllByOrderById();

    Optional<MainCategory> findByTitle(String genre);
}
