package com.example.cybage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.cybage.entity.Course;
@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{


}
