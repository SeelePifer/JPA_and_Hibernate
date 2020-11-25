package com.practicando.jpa.demo;





import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.practicando.jpa.demo.DemoApplication;
import com.practicando.jpa.demo.entity.Course;
import com.practicando.jpa.demo.repository.CourseSpringDataRepository;




@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
class CourseSpringDataRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseSpringDataRepository repository;
	
	@Test
	public void findByid_CoursePresent() {
		Optional<Course> courseOptional = repository.findById(10001L);
		assertTrue(courseOptional.isPresent());
	}
	@Test
	public void findByid_CourseNotPresent() {
		Optional<Course> courseOptional = repository.findById(20001L);
		assertFalse(courseOptional.isPresent());
	}
	@Test
	public void playingAroundSpringDataRepository() {
		/*Course course = new Course("Microservices in 100 Steps");
		repository.save(course);
		course.setName("Microservices in 100 Steps -> Updated");
		repository.save(course);*/
		logger.info("Courses ->{}",repository.findAll());
		logger.info("Count ->{}",repository.count());
	}
	
	/**
	 * @author Luis Felipe
	 *Using Sort class 2.4.1 API
	 */
	@Test
	public void sort() {
		Sort sort = Sort.by(Sort.Direction.DESC, "name");
		logger.info("Sorted Courses -> {} ", repository.findAll(sort));
		//Courses -> [Course[JPA in 50 Steps], Course[Spring in 50 Steps], Course[Spring Boot in 100 Steps]] 
	}
	/**
	 * @author Luis Felipe
	 *Using PageRequest class 2.4.1 API, now use PageRequest.of, I had problems with constructor
	 */
	@Test
	public void pagination() {
		PageRequest pageRequest = PageRequest.of(0, 3);
		
		Page<Course> firstPage = repository.findAll(pageRequest);
		logger.info("First Page->{}",firstPage.getContent());
		
		Pageable secondPageable = firstPage.nextPageable();
		Page<Course> secondPage = repository.findAll(secondPageable);
		logger.info("Second Page -> {}",secondPage.getContent());
		
	}
	@Test
	public void findUsingName() {
		logger.info("FindByName -> {}",repository.findByName("JPA in 50 steps"));
	}
	
	
}
