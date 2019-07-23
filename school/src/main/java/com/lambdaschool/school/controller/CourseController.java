package com.lambdaschool.school.controller;

import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/courses")
public class CourseController
{
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    // http://localhost:2019/courses/courses/
    // http://localhost:2019/courses/courses/?page=1&size=3
    // http://localhost:2019/courses/courses/?page=1&size=3&sort=courseid,asc

    @GetMapping(value = "/courses", produces = {"application/json"})
    public ResponseEntity<?> listAllCourses(@PageableDefault(page = 0, size = 3) Pageable pageable, HttpServletRequest request)
    {
        logger.trace(request.getRequestURI() + request.getMethod() + " accessed"); // reads request URI from client, allows it to be more dynamic.
        ArrayList<Course> myCourses = courseService.findAll(pageable);
        return new ResponseEntity<>(myCourses, HttpStatus.OK);
    }

    @GetMapping(value ="/allcourses", produces = {"application/json"})
    public ResponseEntity<?> noSortListAllCourses()
    {
        ArrayList<Course> myCourses = courseService.findAll();
        return new ResponseEntity<>(myCourses, HttpStatus.OK);
    }

    @GetMapping(value = "/studcount", produces = {"application/json"})
    public ResponseEntity<?> getCountStudentsInCourses(HttpServletRequest request)
    {
        logger.trace(request.getRequestURI() + request.getMethod() + " accessed");
        return new ResponseEntity<>(courseService.getCountStudentsInCourse(), HttpStatus.OK);
    }

    @DeleteMapping("/courses/{courseid}")
    public ResponseEntity<?> deleteCourseById(HttpServletRequest request, @PathVariable long courseid)
    {
        logger.trace(request.getRequestURI() + " deleted");
        courseService.delete(courseid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
