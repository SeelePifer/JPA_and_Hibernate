package com.practicando.jpa.demo.repository;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.practicando.jpa.demo.entity.Course;
import com.practicando.jpa.demo.entity.Passport;
import com.practicando.jpa.demo.entity.Student;

@Repository
@Transactional
public class StudentRepository {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	EntityManager em;
	
	public Student findById(Long id) {
		return em.find(Student.class, id);
	}
	public void deleteById(long id) {
		Student student=findById(id);
		 em.remove(student);
	}
	public Student save(Student student) {
		if(student.getId()==null) {
			//Insert
			em.persist(student);
		}else {
			//Update
			em.merge(student);
		}
		return student;
	}
	public void saveStudentWithPassport() {
		Passport passport = new Passport("Z123456");
		em.persist(passport);
		Student student = new Student("Mike");
		student.setPassport(passport);
		em.persist(student);
	}
	public void someOperationToUnderstandPersistenceContext() {
		Student student = em.find(Student.class, 20002L);
		
		Passport passport = student.getPassport();
		
		passport.setNumber("E1234567");
		
		student.setName("Ranga- Updated");
	}
	//public Student save(Student student)-->insert or update
	
	//public void deleteById(Long id)
	@Test
	@Transactional
	public void retrievePassportAndAssociatedStudent() {
		Passport passport = em.find(Passport.class, 40001L);
		logger.info("passport ->{}", passport);
		logger.info("student ->{}", passport.getStudent());
	}
	@Test
	@Transactional
	public void retrieveStudentAndAssociatedPassport() {
		Student student = em.find(Student.class, 20001L);
		logger.info("student ->{}", student);
		logger.info("passport ->{}", student.getPassport());
	}
	public void insertHardcodedStudentAndCourse() {
		Student student = new Student("Jack");
		Course course = new Course("Microservices in 100 steps");
		em.persist(student);
		em.persist(course);
		
		student.addCourses(course);
		course.addStudents(student);
		
		em.persist(student);
	}
	public void insertStudentAndCourse(Student student, Course course) {
		student.addCourses(course);
		course.addStudents(student);
		em.persist(course);
		em.persist(student);
		
	}
}
