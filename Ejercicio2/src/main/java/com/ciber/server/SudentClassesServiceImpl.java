package com.ciber.server;

import com.ciber.dao.IStudentClassesDao;
import com.ciber.model.StudentClasses;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SudentClassesServiceImpl implements IStudentClassesService {
  
  Logger log = LoggerFactory.getLogger(this.getClass());
  @Autowired
  private IStudentClassesDao dao;

  @Override
  public List<StudentClasses> findAll() {
    
    return (List<StudentClasses>) dao.findAll();
  }

  @Override
  public StudentClasses create(StudentClasses stu) {

    return dao.save(stu);
  }

  @Override
  public StudentClasses update(StudentClasses stu) {

    return dao.save(stu);
  }

  @Override
  public int delete(Integer id) {

    int rpta = 0;
    try {
      dao.deleteById(id);
      rpta = 1;
    } catch (Exception e) {
      rpta = 0;
      log.info("error " + e);
    }
    log.info("termino proceso");
    return rpta;
  }

  @Override
  public Optional<StudentClasses> findByID(int id) {

    return dao.findById(id);
  }

}
