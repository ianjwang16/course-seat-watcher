package com.seatwatcher.seatwatcher.controller;

import com.seatwatcher.seatwatcher.model.Course;
import com.seatwatcher.seatwatcher.service.CourseService;
import com.seatwatcher.seatwatcher.service.CourseScraperService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import jakarta.validation.Valid;


import java.util.List;

@RestController
public class CourseController {

    private final CourseService courseService;
    private final CourseScraperService courseScraperService;

    public CourseController(
            CourseService courseService,
            CourseScraperService courseScraperService) {

        this.courseService = courseService;
        this.courseScraperService = courseScraperService;
    }

    // GET all courses
    @GetMapping("/courses")
    public List<Course> getCourses() {
        return courseService.getAllCourses();
    }

    // ADD a new course
    @PostMapping("/courses")
    public Course addCourse(@Valid @RequestBody Course course) {
        return courseService.addCourse(course);
    }

    @GetMapping("/courses/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @PutMapping("/courses/{id}")
    public Course updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody Course course) {

        return courseService.updateCourse(id, course);
    }

    @DeleteMapping("/courses/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }

    @GetMapping("/courses/code/{courseCode}")
    public List<Course> getCoursesByCode(
            @PathVariable String courseCode) {

        return courseService.getCoursesByCode(courseCode);
    }

    @GetMapping("/courses/open")
    public List<Course> getOpenCourses() {
        return courseService.getOpenCourses();
    }

    @PutMapping("/courses/{id}/watch")
    public Course watchCourse(@PathVariable Long id) {
        return courseService.setWatched(id, true);
    }

    @PutMapping("/courses/{id}/unwatch")
    public Course unwatchCourse(@PathVariable Long id) {
        return courseService.setWatched(id, false);
    }

    @GetMapping("/test-scrape")
    public int testScrape() {

        return courseScraperService.getAvailableSeats(
                "CMSC351",
                "0201"
        );
    }
}