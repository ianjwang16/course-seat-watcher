package com.seatwatcher.seatwatcher.service;

import com.seatwatcher.seatwatcher.model.Course;
import com.seatwatcher.seatwatcher.repository.CourseRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseMonitorService {

    private final CourseRepository courseRepository;

    public CourseMonitorService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Scheduled(fixedRate = 30000)
    public void checkCourseSeats() {

        List<Course> courses = courseRepository.findAll();

        for (Course course : courses) {

            if (course.getPreviousSeats() == 0
                    && course.getAvailableSeats() > 0) {

                System.out.println(
                        "NEW SEAT AVAILABLE: "
                                + course.getCourseCode()
                                + " "
                                + course.getSection()
                );
            }

            course.setPreviousSeats(
                    course.getAvailableSeats()
            );

            courseRepository.save(course);
        }
    }
}
