package com.shope.shopmart.Repository;

import com.shope.shopmart.Entities.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository   extends JpaRepository<Category, Integer> {
}
