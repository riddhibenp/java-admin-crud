package com.example.cybage.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cybage.entity.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

	

	

}
