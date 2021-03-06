package com.practicando.jpa.demo;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.practicando.jpa.demo.entity.Course;
import com.practicando.jpa.demo.repository.CourseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
class JPQLTEST {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	EntityManager em;
	
	@Test
	void jpql_basic() {
		Query query = em.createQuery("Select c from Course c");
		List resultList = query.getResultList();
		logger.info("Select c from Course c -> {}",resultList);
	}
	@Test
	void jpql_typed() {
		TypedQuery<Course> createQuery = em.createQuery("Select c from Course c",Course.class);
		List resultList = createQuery.getResultList();
		logger.info("Select c from Course c -> {}",resultList);
	}
	@Test
	void jpql_where() {
		TypedQuery<Course> createQuery = em.createQuery("Select c from Course c where name like'%100 Steps'"
				,Course.class);
		List resultList = createQuery.getResultList();
		logger.info("Select c from Course c where name like'%100 Steps' -> {}",resultList);
	}
	
	@Test
	void criteria_API() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		Root<Course> courseRoot = cq.from(Course.class);
		
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		
		List<Course> resultList = query.getResultList();
		logger.info("Details of all courses ->{}",resultList);
		
	}
}
