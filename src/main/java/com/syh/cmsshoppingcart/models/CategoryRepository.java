package com.syh.cmsshoppingcart.models;

import com.syh.cmsshoppingcart.models.data.Category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);

    Category findBySlug(String slug);

    List<Category> findAllByOrderBySortingAsc();
}