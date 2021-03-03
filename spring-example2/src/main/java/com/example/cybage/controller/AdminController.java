package com.example.cybage.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.cybage.service.AdminServiceImpl;

import io.micrometer.core.ipc.http.HttpSender.Request;

import com.example.cybage.entity.Category;
import com.example.cybage.entity.Course;
import com.example.cybage.entity.Video;

@RestController
@CrossOrigin
public class AdminController {

	@Autowired
	private AdminServiceImpl asi;
	
	List<Category> listOfCategories ;
	List<Course> listOfCourse;
	List<Video> listOfVideo ;

	// show all categories
	@GetMapping("/category/getAllCategories")
	public ResponseEntity<List<Category>> AllCategory() {

		 listOfCategories = asi.getAllCategory();
		for (Category l :  listOfCategories) {
			System.out.println(l);
		}
		return ResponseEntity.status(HttpStatus.OK).body( listOfCategories);
	}

	// show category by id
	@GetMapping(value = "/category/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Optional<Category> CategoryById(@PathVariable int id) {
		 Optional<Category> ctr = listOfCategories.stream().filter(u -> u.getCategoryId() == id).findFirst();
		 if (!ctr.isPresent()) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "given category not found in database");
	        }
		return asi.getCategoryById(id);

	}

	// add category
	@PostMapping("/category/add")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String addCategory(@RequestBody Category c) {
		 
		 if(c.getCategoryId()==0)
			{
			 asi.addCategory(c);	
			}
			else
			{	
				asi.updateCategory(c);
			}
			return "redirect:/getAllCategories";

	}

	// delete category by id
	@DeleteMapping("/category/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable int id) {
		listOfCategories =asi.getAllCategory();
		
		boolean isDeleted = listOfCategories .removeIf(u -> u.getCategoryId() == id); // java 8
		 asi.deleteCategory(id);
        if (!isDeleted) {
            throw new ResponseStatusException(HttpStatus.OK, "given category could not be deleted as it is not present in db");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Category deleted !!!");

	}

	// update category by id
	@PutMapping("/category/{cat_id}")
	public boolean updateCategory(@RequestBody Category c, @PathVariable int cat_id) {
		c.setCategoryId(cat_id);
		return asi.updateCategory(c);

	}

	// show all courses
	@GetMapping("/course/getAllCourses")
	public ResponseEntity<List<Course>> AllCourse() {
		listOfCourse = asi.getAllCourse();
		for (Course l : listOfCourse) {
			System.out.println(l);
		}
		return ResponseEntity.status(HttpStatus.OK).body(listOfCourse);
	}

	// show course by id
	@GetMapping("/course/{id}")
	public Optional<Course> CourseById(@PathVariable int id) {
		
		 Optional<Course> ctr = listOfCourse.stream().filter(u -> u.getCourseId() == id).findFirst();
		 if (!ctr.isPresent()) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "given course not found in database");
	        }
		return asi.getCourseById(id);

	}

	// add course
	@PostMapping("/course/{cat_id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String addCourse(@RequestBody Course c, @PathVariable int cat_id) {
		
		 if(c.getCourseId()==0)
			{
			 asi.addCourse(c, cat_id);	
			}
			else
			{	
				 asi.updateCourse(c);
			}
			return "redirect:/getAllCourses";

	}

	// delete course by id
	@DeleteMapping("/course/{id}")
	public ResponseEntity<String> deleteCourse(@PathVariable int id) {
		//asi.deleteCourse(id);
		listOfCourse =asi.getAllCourse();
		boolean isDeleted = listOfCourse .removeIf(u -> u.getCourseId() == id); // java 8
		
        if (!isDeleted) {
            throw new ResponseStatusException(HttpStatus.OK, "given course could not be deleted as it is not present in db");
        }
        
       asi.deleteCourse(id);
        return ResponseEntity.status(HttpStatus.OK).body("Course deleted !!!");
	}

	// update category by id
	@PutMapping("/course/{co_id}")
	public boolean updateCourse(@RequestBody Course c, @PathVariable int co_id) {
		c.setCourseId(co_id);
		return asi.updateCourse(c);

	}

	// show all videos
	@GetMapping("/video/getAllVideos")
	public ResponseEntity<List<Video>> AllVideos() {
		List<Video> li2 = asi.getAllVideo();
		for (Video l : li2) {
			System.out.println(l);
		}
		return ResponseEntity.status(HttpStatus.OK).body(li2);
	}

	// show video by id
	@GetMapping("/video/{id}")
	public Optional<Video> VideoById(@PathVariable int id) {
		 Optional<Video> ctr = listOfVideo.stream().filter(u -> u.getVideoId() == id).findFirst();
		 if (!ctr.isPresent()) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "given video not found in database");
	        }
		return asi.getVideoById(id);
	}

	// add video
	@PostMapping("/video/add")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String addVideo(@RequestBody Video c) {
		 if(c.getVideoId()==0)
			{
			 asi.addVideo(c);	
			}
			else
			{	
				asi.updateVideo(c);
			}
			return "redirect:/getAllVideos";
	}

	// delete video
	@DeleteMapping("/video/{id}")
	public ResponseEntity<String> deleteVideo(@PathVariable int id) {
	boolean isDeleted = listOfVideo .removeIf(u -> u.getVideoId() == id); // java 8
		
        if (!isDeleted) {
            throw new ResponseStatusException(HttpStatus.OK, "given video could not be deleted as it is not present in db");
        }
        asi.deleteVideo(id);
        return ResponseEntity.status(HttpStatus.OK).body("Video deleted !!!");
	}

	// update video by id
	@PutMapping("/video/{v_id}")
	public boolean updateVideo(@RequestBody Video v, @PathVariable int v_id) {
		v.setVideoId(v_id);
		return asi.updateVideo(v);
	}

}
