package com.seatwatcher.seatwatcher.controller;

import com.seatwatcher.seatwatcher.model.Course;
import com.seatwatcher.seatwatcher.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    private final CourseService courseService;

    public WebController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute(
                "courses",
                courseService.getAllCourses()
        );

        model.addAttribute(
                "newCourse",
                new Course()
        );

        return "index";
    }

    @PostMapping("/web/courses")
    public String addCourse(Course course) {

        courseService.addCourse(course);

        return "redirect:/";
    }

    @PostMapping("/web/courses/{id}/watch")
    public String watchCourse(
            @PathVariable Long id) {

        courseService.setWatched(id, true);

        return "redirect:/";
    }

    @PostMapping("/web/courses/{id}/unwatch")
    public String unwatchCourse(
            @PathVariable Long id) {

        courseService.setWatched(id, false);

        return "redirect:/";
    }

    @PostMapping("/web/courses/{id}/delete")
    public String deleteCourse(
            @PathVariable Long id) {

        courseService.deleteCourse(id);

        return "redirect:/";
    }
}
