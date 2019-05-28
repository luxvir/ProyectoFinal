package com.ciber.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ciber.exception.ModeloNotFoundException;
import com.ciber.model.Classes;
import com.ciber.server.IClassesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Spring Boot Swagger rest", description = "Mostar información")
@RestController
public class ClassesController {

  Logger log = LoggerFactory.getLogger(this.getClass());
  
  @Autowired
  private IClassesService service;
  
  /**
   * La función listFamiliesthat() retorna la lista de Classes.
   * 
   * @return list of families.
   */
  @ApiOperation(value = "Retorna lista de classes")
  @GetMapping(value = "/api/v1/classes")
  public ResponseEntity<List<Classes>> listClasses() {
    return new ResponseEntity<List<Classes>>(service.findAll(), HttpStatus.OK);
  }
  
  /**
   * La función createClasses() se encarga de registrar a un objeto classes.
   * 
   * @param cla object classes.
   * @return object classes.
   */
  @ApiOperation(value = "Crea a una classes")
  @PostMapping(value = "/api/v1/classes", consumes = "application/json", 
        produces = "application/json")
  public ResponseEntity<Classes> createClasses(@RequestBody Classes cla) {
    
    return new ResponseEntity<Classes>(service.create(cla), HttpStatus.CREATED);
  }
 
  /**
   * La función updatefamilies() se encarga de actualizar a un objeto familia.
   * 
   * @param fami object families.
   * @return object families.
   */
  @ApiOperation(value = "Actualiza a una families")
  @PutMapping(value = "/api/v1/families", consumes = "application/json", 
        produces = "application/json")
  public ResponseEntity<Classes> updateClasses(@RequestBody Classes cla) {
    String mensaje="";
    Optional<Classes> c = service.findByID(cla.getClassesId());
    
    if(c.isPresent()) {
      return new ResponseEntity<Classes>(service.update(cla), HttpStatus.OK);
    }else {
      mensaje = "error "+cla.getClassesId();
      throw  new ModeloNotFoundException(mensaje);
    }    
  }
  
}
