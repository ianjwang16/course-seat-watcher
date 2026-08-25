package com.seatwatcher.seatwatcher.controller;

import com.seatwatcher.seatwatcher.model.Course;
import com.seatwatcher.seatwatcher.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.seatwatcher.seatwatcher.service.CourseScraperService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    private final CourseService courseService;
    private final CourseScraperService courseScraperService;

    public WebController(
            CourseService courseService,
            CourseScraperService courseScraperService) {

        this.courseService = courseService;
        this.courseScraperService = courseScraperService;
    }

    @PostMapping("/web/courses")
    public String addCourse(
            @RequestParam String courseCode,
            @RequestParam String section,
            @RequestParam String term) {

        int availableSeats =
                courseScraperService.getAvailableSeats(
                        term,
                        courseCode,
                        section
                );

        if (availableSeats == -1) {
            return "redirect:/?error=Course or section not found on Testudo";
        }

        Course course =
                new Course(courseCode, section, availableSeats);

        course.setTerm(term);

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

    @GetMapping("/")
    public String home(
            Model model,
            @RequestParam(required = false) String error) {

        model.addAttribute(
                "courses",
                courseService.getAllCourses()
        );

        model.addAttribute(
                "terms",
                courseScraperService.getAvailableTerms()
        );

        model.addAttribute("error", error);

        return "index";
    }
}
