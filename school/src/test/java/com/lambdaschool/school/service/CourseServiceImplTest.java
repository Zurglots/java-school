package com.lambdaschool.school.service;


import com.lambdaschool.school.SchoolApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class) // declares we want to use with spring jpa
@SpringBootTest(classes = SchoolApplication.class)
public class CourseServiceImplTest
{
    @Autowired CourseService courseService;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    // Mocking Vs Stubbing
    // Mocking set up special data to do something.
    // Stubbing - don't use the real version of the method, force it to return something different.
    // Everything in spring is referred to as mocking.

    @Test
    public void findCourseById()
    {
        assertEquals (1, courseService.findCourseById(1).getCourseid());
    }

    @Test
    public void findAll()
    {
        assertEquals(12, courseService.findAll().size());
    }

}
