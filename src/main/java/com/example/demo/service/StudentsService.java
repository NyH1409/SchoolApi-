package com.example.demo.service;

import com.example.demo.model.Students;
import com.example.demo.repository.StudentsRepository;
import lombok.AllArgsConstructor;


import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class StudentsService {
  private StudentsRepository repository;

  public List<Students> findAll(){
    return repository.findAll();
  }

  public Students save(Students student){
    return repository.save(student);
  }
  public void deleteById(Long id_student){
    repository.deleteById(id_student);
  }
  public List<Students> findWhereNameLike(String query){
    return repository.findByNameContainingIgnoreCase(query);
  }

  public Students findById(Long id){
    return repository.findById_student(id);
  }
}
