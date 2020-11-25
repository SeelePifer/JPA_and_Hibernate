package com.practicando.jpa.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.practicando.jpa.demo.entity.Course;

/**
 * @author Luis Felipe
 *
 *Using SpringData JPA , in format JpaRepository<Class, Id> and them extends CRUDRepository
 */
@RepositoryRestResource(path = "courses")
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {
	
	List<Course> findByNameAndId(String name,Long id);
	List<Course> findByName(String name);
	List<Course> countByName(String name);
	List<Course> findByNameOrderByIdDesc(String name);
	List<Course> deleteByName(String name);
	
	@Query("Select c From Course c where name like '%100 Steps'")
	List<Course> courseWith100StepsInName();
	
	@Query(value = "Select  *  From Course c where name like '%100 Steps'", nativeQuery = true)
	List<Course> courseWith100StepsInNameUsingNativeQuery();
	
}
