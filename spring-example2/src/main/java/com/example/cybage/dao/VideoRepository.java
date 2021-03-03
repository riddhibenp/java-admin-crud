package com.example.cybage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cybage.entity.Category;
import com.example.cybage.entity.Video;
@Repository
public interface VideoRepository extends JpaRepository<Video, Integer>{

	

}
