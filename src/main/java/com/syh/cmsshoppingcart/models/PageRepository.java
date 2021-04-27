package com.syh.cmsshoppingcart.models;

import com.syh.cmsshoppingcart.models.data.Page;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PageRepository extends JpaRepository<Page, Integer> {
    Page findBySlug(String slug);

    Page findBySlugAndIdNot(String slug, int id);

    List<Page> findAllByOrderBySortingAsc();
}
