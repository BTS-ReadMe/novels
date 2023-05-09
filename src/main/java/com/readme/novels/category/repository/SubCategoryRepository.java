package com.readme.novels.category.repository;

import com.readme.novels.category.model.SubCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    List<SubCategory> findByMainCategoryId(Long mainCategoryId);
}
