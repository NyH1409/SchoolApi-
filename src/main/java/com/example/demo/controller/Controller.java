package com.example.demo.controller;

import com.example.demo.model.Students;
import com.example.demo.service.JDBCStudentsService.JDBCStudentsService;
import com.example.demo.service.JPQLService.JPQLStudentsService;
import com.example.demo.service.StudentsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@AllArgsConstructor
public class Controller {
  private final StudentsService service;
  private final JDBCStudentsService jdbcservice;

  private final JPQLStudentsService jpqlservice;
  

  @GetMapping("/")
  public String hello(){
    return "Hello World";
  }

  @GetMapping("/students")
  public List<Students> getAllStudents(){
    return service.findAll();
  }

  @GetMapping("/students/search")
  public List<Students> getStudentsByNameContent(@RequestParam(name = "q", required = true) String query){
    return service.findWhereNameLike(query);
  }

  @PostMapping("/students")
  public Students createStudents(Students student){
    return service.save(student);
  }

  @PatchMapping("/students")
  public Students updateStudents(
          @RequestParam("id") Long id,
          @RequestParam("name") String newName
  ){
    Students student = service.findById(id);
    student.setName(newName);
    return service.save(student);
  }

  @DeleteMapping("/students/{id_student}")
  public String deleteStudents(@PathVariable Long id_student){
    service.deleteById(id_student);
    return "Resource successfully deleted";
  }

  @GetMapping("/jdbc/students")
  public List<Students> getAllStudentsUsingJDBC() throws SQLException, ClassNotFoundException, IOException {
    return jdbcservice.findAll();
  }

  @GetMapping("/jdbc/students/search")
  public List<Students> getStudentsByNameContentUsingJDBC(@RequestParam(name = "q") String query) throws SQLException, IOException, ClassNotFoundException {
    return jdbcservice.findWhereNameLike(query);
  }

  @PostMapping("/jdbc/students")
  public Students createStudentsUsingJDBC(@RequestBody Students student) throws SQLException, IOException, ClassNotFoundException {
    return jdbcservice.save(student);
  }

  @PatchMapping("/jdbc/students")
  public Students updateStudentsUsingJDBC(
          @RequestParam("id") Long id,
          @RequestParam("name") String newName
          ) throws SQLException, IOException, ClassNotFoundException {
    return jdbcservice.updateNameById(id,newName);
  }

  @DeleteMapping("/jdbc/students/{id}")
  public String deleteStudentsUsingJDBC(@PathVariable Long id) throws SQLException, IOException, ClassNotFoundException {
    jdbcservice.deleteById(id);
    return "Resource successfully deleted";
  }

  @GetMapping("/jpql/students")
  public List<Students> getAllStudentsUsingJPQL(){
    return jpqlservice.findAll();
  }

  @GetMapping("/jpql/students/search")
  public List<Students> getStudentsByNameContentUsingJPQL(@RequestParam("q") String query){
    return jpqlservice.findWhereNameLike(query);
  }

  @PostMapping("/jpql/students")
  public Students createStudentsUsingJPQL(@RequestBody Students student){
    return jpqlservice.save(student);
  }

  @PatchMapping("/jpql/students")
  public Students updateStudentsUsingJPQL(
          @RequestParam("id") Long id,
          @RequestParam("name") String name
  ){
    return jpqlservice.updateNameById(id, name);
  }

  @DeleteMapping("/jpql/students/{id}")
  public String deleteStudentsUsingJPQL(@PathVariable Long id){
    jpqlservice.deleteById(id);
    return "Resource successfully deleted";
  }
}
