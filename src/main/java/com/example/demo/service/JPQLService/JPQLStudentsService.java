package com.example.demo.service.JPQLService;

import com.example.demo.model.Students;
import com.example.demo.repository.JPQLrepository.JPQLStudentsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JPQLStudentsService {
  private JPQLStudentsRepository repository;

  public List<Students> findAll(){
    return repository.findAll();
  }

  public Students save(Students student){
    return repository.save(student);
  }

  public void deleteById(Long id){
    repository.deleteById(id);
  }

  public Students updateNameById(Long id, String newName){
    return repository.updateNameById(id,newName);
  }
  public List<Students> findWhereNameLike(String query){
    return repository.findWhereNameLike(query);
  }
}