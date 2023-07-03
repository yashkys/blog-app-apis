package com.kys.blog_app_bakend.repositories;

import com.kys.blog_app_bakend.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
