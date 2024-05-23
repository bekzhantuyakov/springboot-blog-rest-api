package com.springbootblog.springbootblogrestapi.repository;

import com.springbootblog.springbootblogrestapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {


}
