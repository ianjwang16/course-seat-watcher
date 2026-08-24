package com.seatwatcher.seatwatcher.service;

import com.seatwatcher.seatwatcher.model.Course;
import com.seatwatcher.seatwatcher.repository.CourseRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseMonitorService {

    private final CourseRepository courseRepository;
    private final EmailService emailService;

    public CourseMonitorService(
            CourseRepository courseRepository,
            EmailService emailService) {

        this.courseRepository = courseRepository;
        this.emailService = emailService;
    }

    @Scheduled(fixedRate = 30000)
    public void checkCourseSeats() {

        List<Course> courses = courseRepository.findByWatchedTrue();

        for (Course course : courses) {

            if (course.getPreviousSeats() == 0
                    && course.getAvailableSeats() > 0) {

                System.out.println(
                        "NEW SEAT AVAILABLE: "
                                + course.getCourseCode()
                                + " "
                                + course.getSection()
                );

                emailService.sendSeatAvailableEmail(
                        "YOUR_EMAIL@gmail.com",
                        course.getCourseCode(),
                        course.getSection(),
                        course.getAvailableSeats()
                );
            }

            course.setPreviousSeats(
                    course.getAvailableSeats()
            );

            courseRepository.save(course);
        }
    }
}
