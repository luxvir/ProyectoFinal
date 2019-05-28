package com.ciber.dao;

import com.ciber.model.StudentClasses;
import org.springframework.data.repository.CrudRepository;



public interface IStudentClassesDao extends  CrudRepository<StudentClasses, Integer> {

}
