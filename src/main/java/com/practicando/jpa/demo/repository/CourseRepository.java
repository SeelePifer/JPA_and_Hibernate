package com.practicando.jpa.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.practicando.jpa.demo.entity.Course;
import com.practicando.jpa.demo.entity.Review;

@Repository
@Transactional
public class CourseRepository  {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	EntityManager em;
	
	public Course findById(Long id) {
		return em.find(Course.class, id);
	}
	public void deleteById(long id) {
		Course course=findById(id);
		 em.remove(course);
	}
	public Course save(Course course) {
		if(course.getId()==null) {
			//Insert
			em.persist(course);
		}else {
			//Update
			em.merge(course);
		}
		return course;
	}
	public void playWithEntityManager() {
		Course course1 = new Course("Web Services in 100 steps");
		em.persist(course1);
		
		Course course2 = findById(1001L);
		course2.setName("JPA in 50 steps-Updated");
	}
	//public Course save(Course course)-->insert or update
	public void addHardcodedReviewsForCourse() {
		//get the course 10003
		Course course = findById(10003L);
		logger.info("course.getReviews() -> {}", course.getReviews());
		
		//add 2 reviews to it
		Review review1 = new Review("5", "Great Hands-on Stuff.");	
		Review review2 = new Review("5", "Hatsoff.");
		
		//setting the relationship
		course.addReview(review1);
		review1.setCourse(course);
		
		course.addReview(review2);
		review2.setCourse(course);
		
		//save it to the database
		em.persist(review1);
		em.persist(review2);
	}
	
	public void addReviewsForCourse(Long courseId, List<Review> reviews) {    
	    Course course = findById(courseId);
	    logger.info("course.getReviews() -> {}", course.getReviews());
	    for(Review review:reviews)
	    {     
	      //setting the relationship
	      course.addReview(review);
	      review.setCourse(course);
	      em.persist(review);
	    }
	}
	}
	
