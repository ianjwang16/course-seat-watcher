package com.seatwatcher.seatwatcher.service;

import com.seatwatcher.seatwatcher.model.Course;
import com.seatwatcher.seatwatcher.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public Course updateCourse(Long id, Course updatedCourse) {

        Course course = courseRepository.findById(id).orElse(null);

        if (course == null) {
            return null;
        }

        course.setCourseCode(updatedCourse.getCourseCode());
        course.setSection(updatedCourse.getSection());
        course.setAvailableSeats(updatedCourse.getAvailableSeats());

        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public List<Course> getCoursesByCode(String courseCode) {
        return courseRepository.findByCourseCode(courseCode);
    }

    public List<Course> getOpenCourses() {
        return courseRepository.findByAvailableSeatsGreaterThan(0);
    }

    public Course setWatched(Long id, boolean watched) {

        Course course = courseRepository.findById(id).orElse(null);

        if (course == null) {
            return null;
        }

        course.setWatched(watched);

        return courseRepository.save(course);
    }


}
