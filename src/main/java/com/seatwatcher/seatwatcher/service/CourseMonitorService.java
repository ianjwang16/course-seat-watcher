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
    private final CourseScraperService courseScraperService;

    public CourseMonitorService(
            CourseRepository courseRepository,
            EmailService emailService,
            CourseScraperService courseScraperService) {

        this.courseRepository = courseRepository;
        this.emailService = emailService;
        this.courseScraperService = courseScraperService;
    }

    @Scheduled(fixedRate = 30000)
    public void checkCourseSeats() {

        List<Course> courses =
                courseRepository.findByWatchedTrue();

        for (Course course : courses) {

            int oldSeats = course.getAvailableSeats();

            int newSeats =
                    courseScraperService.getAvailableSeats(
                            course.getTerm(),
                            course.getCourseCode(),
                            course.getSection()
                    );

            if (newSeats == -1) {
                System.out.println(
                        "Could not retrieve seat data for "
                                + course.getCourseCode()
                                + " "
                                + course.getSection()
                );

                continue;
            }

            course.setPreviousSeats(oldSeats);
            course.setAvailableSeats(newSeats);

            if (oldSeats == 0 && newSeats > 0) {

                System.out.println(
                        "NEW SEAT AVAILABLE: "
                                + course.getCourseCode()
                                + " "
                                + course.getSection()
                                + " Seats: "
                                + newSeats
                );

                emailService.sendSeatAvailableEmail(
                        "YOUR_EMAIL@gmail.com",
                        course.getCourseCode(),
                        course.getSection(),
                        newSeats
                );
            }

            courseRepository.save(course);
        }
    }

}
