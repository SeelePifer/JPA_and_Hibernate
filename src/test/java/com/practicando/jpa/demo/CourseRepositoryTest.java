package com.practicando.jpa.demo;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.practicando.jpa.demo.entity.Course;
import com.practicando.jpa.demo.repository.CourseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
class CourseRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	CourseRepository courseRepository;
	
	@Test
	void findById_basic() {
		Course course =courseRepository.findById(1001L);
		assertEquals("JPA in 50 steps", course.getName());
	}
	@Test
	@DirtiesContext
	void deleteById_basic() {
		courseRepository.deleteById(1002L);;
		assertNull(courseRepository.findById(1002L));
		//assertEquals("JPA in 50 steps", course.getName());
	}
	@Test
	@DirtiesContext
	void save_basic() {
		//get a course
		Course course =courseRepository.findById(1001L);
		assertEquals("JPA in 50 steps", course.getName());
		
		//update details
		course.setName("JPA in 50 steps-Updated");
		courseRepository.save(course);
		
		Course course1 =courseRepository.findById(1001L);
		assertEquals("JPA in 50 steps-Updated", course1.getName());
		//assertEquals("JPA in 50 steps", course.getName());
	}
	@Test
	@DirtiesContext
	void playWithEntityManager() {
		//get a course
		courseRepository.playWithEntityManager();
	}
	
}
