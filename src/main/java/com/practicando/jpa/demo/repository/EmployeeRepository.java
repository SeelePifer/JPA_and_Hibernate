package com.practicando.jpa.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.practicando.jpa.demo.entity.Course;
import com.practicando.jpa.demo.entity.Employee;
import com.practicando.jpa.demo.entity.Review;

@Repository
@Transactional
public class EmployeeRepository  {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	EntityManager em;
	
	
	public void insertEmployee(Employee employee) {
		em.persist(employee);
	}
	
	
	public List<Employee> retrieveAllEmployee(){
		return em.createQuery("select e from Employee e",Employee.class)
				.getResultList();
	}
	}
	
