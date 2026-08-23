package com.seatwatcher.seatwatcher.controller;

import com.seatwatcher.seatwatcher.model.Course;
import com.seatwatcher.seatwatcher.service.CourseService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;



import java.util.List;

@RestController
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // GET all courses
    @GetMapping("/courses")
    public List<Course> getCourses() {
        return courseService.getAllCourses();
    }

    // ADD a new course
    @PostMapping("/courses")
    public Course addCourse(@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    @GetMapping("/courses/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @PutMapping("/courses/{id}")
    public Course updateCourse(
            @PathVariable Long id,
            @RequestBody Course course) {

        return courseService.updateCourse(id, course);
    }

    @DeleteMapping("/courses/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }
}