package com.seatwatcher.seatwatcher.repository;

import com.seatwatcher.seatwatcher.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseRepository
        extends JpaRepository<Course, Long> {

    List<Course> findByCourseCode(String courseCode);
    List<Course> findByAvailableSeatsGreaterThan(int seats);
    List<Course> findByWatchedTrue();
}
