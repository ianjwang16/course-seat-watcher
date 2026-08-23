package com.seatwatcher.seatwatcher.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseCode;
    private String section;
    private int availableSeats;

    public Course() {
    }

    public Course(String courseCode, String section, int availableSeats) {
        this.courseCode = courseCode;
        this.section = section;
        this.availableSeats = availableSeats;
    }

    public Long getId() {
        return id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getSection() {
        return section;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}
