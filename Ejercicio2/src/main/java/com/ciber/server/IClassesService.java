package com.ciber.server;

import com.ciber.model.Classes;
import java.util.List;
import java.util.Optional;

public interface IClassesService {
  
  List<Classes> findAll();

  Classes create(Classes cla);

  Classes update(Classes cla);

  int delete(Integer id);
  
  Optional<Classes> findByID(int id);

}
