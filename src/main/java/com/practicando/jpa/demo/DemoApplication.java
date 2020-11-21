package com.practicando.jpa.demo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.practicando.jpa.demo.entity.Course;
import com.practicando.jpa.demo.entity.Review;
import com.practicando.jpa.demo.entity.Student;
import com.practicando.jpa.demo.repository.CourseRepository;
import com.practicando.jpa.demo.repository.StudentRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private StudentRepository srepository;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//srepository.saveStudentWithPassport();
		//repository.playWithEntityManager();
		/*List<Review> reviews = new ArrayList<>();
	    reviews.add(new Review("5", "Great Hands-on Stuff."));  
	    reviews.add(new Review("5", "Hatsoff."));
	    courseRepository.addReviewsForCourse(10003L, reviews ); */
		srepository.insertStudentAndCourse(new Student("Jack"),
				new Course("Microservices in 100 Steps"));
	}

}
