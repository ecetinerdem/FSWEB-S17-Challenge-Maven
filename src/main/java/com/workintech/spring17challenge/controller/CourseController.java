package com.workintech.spring17challenge.controller;

import com.workintech.spring17challenge.entity.CourseResponse;
import com.workintech.spring17challenge.exceptions.CourseAlreadyExistsException;
import com.workintech.spring17challenge.exceptions.CourseNotFoundException;
import com.workintech.spring17challenge.exceptions.InvalidCreditException;
import com.workintech.spring17challenge.model.Course;
import com.workintech.spring17challenge.model.HighCourseGpa;
import com.workintech.spring17challenge.model.LowCourseGpa;
import com.workintech.spring17challenge.model.MediumCourseGpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

public class CourseController {
    public List<Course> courses;


    @Autowired
    private LowCourseGpa lowCourseGpa;

    @Autowired
    private MediumCourseGpa mediumCourseGpa;

    @Autowired
    private HighCourseGpa highCourseGpa;

    public CourseController() {
        this.courses = new ArrayList<>();

    }
    @GetMapping
    public List<Course> getAllCourses() {
        return courses;
    }

    @GetMapping("/{name}")
    public Course getCourseByName(@PathVariable String name) {
        return courses.stream()
                .filter(course -> course.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new CourseNotFoundException(HttpStatus.NOT_FOUND, "Course not found"));
    }

    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(@RequestBody Course course) {
        if (courses.stream().anyMatch(c -> c.getName().equals(course.getName()))) {
            throw new CourseAlreadyExistsException(HttpStatus.BAD_REQUEST, "Course already exists");
        }
        if (course.getCredit() < 0 || course.getCredit() > 4) {
            throw new InvalidCreditException(HttpStatus.NOT_FOUND, "Invalid credit value");
        }
        courses.add(course);
        int totalGpa = calculateTotalGpa(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CourseResponse(course, totalGpa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable int id, @RequestBody Course course) {
        Course existingCourse = courses.get(id);
        if (existingCourse == null) {
            throw new CourseNotFoundException(HttpStatus.NOT_FOUND, "Course not found");
        }
        existingCourse.setName(course.getName());
        existingCourse.setCredit(course.getCredit());
        existingCourse.setGrade(course.getGrade());
        int totalGpa = calculateTotalGpa(existingCourse);
        return ResponseEntity.ok(new CourseResponse(existingCourse, totalGpa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable int id) {
        if (id < 0 || id >= courses.size()) {
            throw new CourseNotFoundException(HttpStatus.NOT_FOUND, "Course not found");
        }
        courses.remove(id);
        return ResponseEntity.ok().build();
    }

    private int calculateTotalGpa(Course course) {
        if (course.getCredit() <= 2) {
            return course.getGrade().getCoefficient() * course.getCredit() * lowCourseGpa.getGpa();
        } else if (course.getCredit() == 3) {
            return course.getGrade().getCoefficient() * course.getCredit() * mediumCourseGpa.getGpa();
        } else {
            return course.getGrade().getCoefficient() * course.getCredit() * highCourseGpa.getGpa();
        }
    }
}