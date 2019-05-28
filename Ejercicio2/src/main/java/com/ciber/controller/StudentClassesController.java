package com.ciber.controller;

import com.ciber.exception.ModeloNotFoundException;
import com.ciber.model.StudentClasses;
import com.ciber.server.IStudentClassesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@Api(value = "Spring Boot Swagger rest", description = "Mostar información")
@RestController
public class StudentClassesController {

  Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private IStudentClassesService service;

  /**
   * La función listStudentClasses() retorna la lista de student Classes.
   * 
   * @return list of student Classes.
   */
  @ApiOperation(value = "Retorna lista de student Classes")
  @GetMapping(value = "/api/v1/studentClasses")
  public ResponseEntity<List<StudentClasses>> listStudentClasses() {
    return new ResponseEntity<List<StudentClasses>>(service.findAll(), HttpStatus.OK);
  }

  /**
   * La función createfamilies() se encarga de registrar a un objeto student
   * Classes.
   * 
   * @param stu object student Classes.
   * @return object student Classes.
   */
  @ApiOperation(value = "Crea a una student Classes")
  @PostMapping(value = "/api/v1/studentClasses", consumes = "application/json", produces = "application/json")
  public ResponseEntity<StudentClasses> createfamilies(@RequestBody StudentClasses stu) {

    return new ResponseEntity<StudentClasses>(service.create(stu), HttpStatus.CREATED);
  }

  /**
   * La función studentClasses() se encarga de actualizar a un objeto student Classes.
   * 
   * @param stuClass object student Classes.
   * @return object student Classes.
   */
  @ApiOperation(value = "Actualiza a una student Classes")
  @PutMapping(value = "/api/v1/studentClasses", consumes = "application/json", 
      produces = "application/json")
  public ResponseEntity<StudentClasses> updateStudentClasse(@RequestBody StudentClasses stuClass) {
    String mensaje = "";
    Optional<StudentClasses> stu = service.findByID(stuClass.getStudents().getStudentId());
    Optional<StudentClasses> cla = service.findByID(stuClass.getClasses().getClassesId());

    if (stu.isPresent() && cla.isPresent()) {
      return new ResponseEntity<StudentClasses>(service.update(stuClass), HttpStatus.OK);
    } else {
      mensaje = "error " + stuClass.getClasses().getClassesId();
      throw new ModeloNotFoundException(mensaje);
    }
  }

  /**
   * La función listById() se le envia un parametro id y retorna a
   * la Classes Classes de ese id.
   * 
   * @param id parametro de filtro.
   * @return id.
   */
  @ApiOperation(value = "Retorna inforacion de student Classes Classes  por su Id")
  @GetMapping(value = "/api/v1/studentClasses/{id}")
  public ResponseEntity<Object> listById(@PathVariable("id") Integer id) {
   
    String mensaje= "";
    Optional<StudentClasses> stu = service.findByID(id);
    
    if ( stu.isPresent() ) {
      return new ResponseEntity<Object>(stu,HttpStatus.OK);
    }else { 
      mensaje="error  "+id;
      throw  new ModeloNotFoundException(mensaje);
    }
  }
}
