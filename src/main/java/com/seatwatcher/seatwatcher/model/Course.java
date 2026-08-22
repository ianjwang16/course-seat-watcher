package com.seatwatcher.seatwatcher.model;

public class Course {

    private int id;
    private String courseCode;
    private String section;
    private int availableSeats;

    public Course(int id, String courseCode,
                  String section, int availableSeats) {
        this.id = id;
        this.courseCode = courseCode;
        this.section = section;
        this.availableSeats = availableSeats;
    }

    public int getId() {
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
}
