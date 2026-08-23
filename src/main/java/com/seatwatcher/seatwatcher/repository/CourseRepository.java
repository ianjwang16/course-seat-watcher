package com.seatwatcher.seatwatcher.repository;

import com.seatwatcher.seatwatcher.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository
        extends JpaRepository<Course, Long> {
}
