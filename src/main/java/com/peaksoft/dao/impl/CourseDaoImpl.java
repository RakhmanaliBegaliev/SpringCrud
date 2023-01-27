package com.peaksoft.dao.impl;

import com.peaksoft.dao.CourseDao;
import com.peaksoft.entity.Course;

import javax.persistence.EntityManager;
import java.util.List;

public class CourseDaoImpl implements CourseDao {

    private EntityManager entityManager;

    @Override
    public List<Course> getAllCourses() {
        return entityManager.createQuery("from Course ").getResultList();
    }

    @Override
    public void addCourse(Course course) {
    entityManager.persist(course);
    }

    @Override
    public void updateCourse(Long id, Course course) {
    Course course1 = entityManager.find(Course.class, id);
    course1.setCourseName(course.getCourseName());
    course1.setDurationMonth(course.getDurationMonth());
    entityManager.merge(course1);
    }

    @Override
    public Course getById(Long id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    public void deleteCourse(Course course) {
    entityManager.remove(course);
    }
}
