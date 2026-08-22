package com.seatwatcher.seatwatcher.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.seatwatcher.seatwatcher.model.Course;

@RestController
public class CourseController {

    @GetMapping("/hello")
    public String hello() {
        return "Seat Watcher is running!";
    }

    @GetMapping("/course")
    public Course getCourse() {

        Course course =
                new Course(1, "CMSC351", "0101", 0);

        return course;
    }
}