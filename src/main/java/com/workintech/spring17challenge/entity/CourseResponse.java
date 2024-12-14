package com.workintech.spring17challenge.entity;

import com.workintech.spring17challenge.model.Course;
import lombok.Data;
import lombok.Getter;

public class CourseResponse {
    private String courseName;
    private int credit;
    private int totalGpa;



    public CourseResponse(Course course, int totalGpa) {
        this.courseName = course.getName();
        this.credit = course.getCredit();
        this.totalGpa = totalGpa;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getTotalGpa() {
        return totalGpa;
    }

    public void setTotalGpa(int totalGpa) {
        this.totalGpa = totalGpa;
    }
}
