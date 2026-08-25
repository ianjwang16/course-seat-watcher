package com.seatwatcher.seatwatcher.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String courseCode;

    @NotBlank
    private String section;

    @Min(0)
    private int availableSeats;

    private int previousSeats;
    private boolean watched;
    private String sourceUrl;

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

    public String getStatus() {
        if (availableSeats > 0) {
            return "OPEN";
        } else {
            return "CLOSED";
        }
    }

    public int getPreviousSeats() {
        return previousSeats;
    }

    public void setPreviousSeats(int previousSeats) {
        this.previousSeats = previousSeats;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }
}
