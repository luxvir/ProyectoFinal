package com.ciber.server;

import com.ciber.dao.IClassesDao;
import com.ciber.model.Classes; 

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ClassesServiceImpl implements IClassesService {

  Logger log = LoggerFactory.getLogger(this.getClass());
  @Autowired
  private IClassesDao dao;
  
  @Override
  public List<Classes> findAll() {

    return (List<Classes>) dao.findAll();
  }

  @Override
  public Classes create(Classes cla) {
    
    return dao.save(cla);
  }

  @Override
  public Classes update(Classes cla) {
    
    return dao.save(cla);
  }

  @Override
  public int delete(Integer id) {
    int rpta = 0;
    try {
      dao.deleteById(id);
      rpta = 1;
      log.info("Eleminado familia");
    } catch (Exception e) {
      rpta = 0;
      log.info("error" + e);
      log.info("no Eleminado familia");
    }
    return rpta;
  }

  @Override
  public Optional<Classes> findByID(int id) {
    
    return dao.findById(id);
  }

}
