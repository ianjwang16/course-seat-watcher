package com.seatwatcher.seatwatcher.service;

import com.seatwatcher.seatwatcher.model.Course;
import com.seatwatcher.seatwatcher.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CourseServiceTest {

    private CourseRepository courseRepository;
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        courseRepository = Mockito.mock(CourseRepository.class);
        courseService = new CourseService(courseRepository);
    }

    @Test
    void getAllCoursesReturnsCourses() {

        Course course =
                new Course("CMSC351", "0101", 0);

        when(courseRepository.findAll())
                .thenReturn(List.of(course));

        List<Course> result =
                courseService.getAllCourses();

        assertEquals(1, result.size());
        assertEquals("CMSC351", result.get(0).getCourseCode());
    }

    @Test
    void addCourseSavesCourse() {

        Course course =
                new Course("CMSC330", "0201", 2);

        when(courseRepository.save(course))
                .thenReturn(course);

        Course result =
                courseService.addCourse(course);

        assertEquals("CMSC330", result.getCourseCode());

        verify(courseRepository).save(course);
    }

    @Test
    void getCourseByIdReturnsCourse() {

        Course course =
                new Course("CMSC420", "0101", 1);

        when(courseRepository.findById(1L))
                .thenReturn(Optional.of(course));

        Course result =
                courseService.getCourseById(1L);

        assertNotNull(result);
        assertEquals("CMSC420", result.getCourseCode());
    }

    @Test
    void getCourseByIdReturnsNullWhenMissing() {

        when(courseRepository.findById(99L))
                .thenReturn(Optional.empty());

        Course result =
                courseService.getCourseById(99L);

        assertNull(result);
    }

    @Test
    void setWatchedUpdatesCourse() {

        Course course =
                new Course("CMSC351", "0101", 0);

        when(courseRepository.findById(1L))
                .thenReturn(Optional.of(course));

        when(courseRepository.save(course))
                .thenReturn(course);

        Course result =
                courseService.setWatched(1L, true);

        assertTrue(result.isWatched());

        verify(courseRepository).save(course);
    }

    @Test
    void deleteCourseDeletesById() {

        Long id = 1L;

        courseService.deleteCourse(id);

        verify(courseRepository).deleteById(id);
    }

    @Test
    void updateCourseUpdatesCourse() {

        Long id = 1L;

        Course existingCourse =
                new Course("CMSC351", "0101", 0);

        Course updatedCourse =
                new Course("CMSC351", "0101", 3);

        when(courseRepository.findById(id))
                .thenReturn(Optional.of(existingCourse));

        when(courseRepository.save(existingCourse))
                .thenReturn(existingCourse);

        Course result =
                courseService.updateCourse(id, updatedCourse);

        assertNotNull(result);

        assertEquals(
                3,
                result.getAvailableSeats()
        );

        verify(courseRepository).findById(id);
        verify(courseRepository).save(existingCourse);
    }

    @Test
    void getOpenCoursesReturnsOnlyOpenCourses() {

        Course course1 =
                new Course("CMSC351", "0101", 3);

        Course course2 =
                new Course("CMSC330", "0201", 1);

        List<Course> openCourses =
                List.of(course1, course2);

        when(courseRepository
                .findByAvailableSeatsGreaterThan(0))
                .thenReturn(openCourses);

        List<Course> result =
                courseService.getOpenCourses();

        assertEquals(2, result.size());

        verify(courseRepository)
                .findByAvailableSeatsGreaterThan(0);
    }
}

