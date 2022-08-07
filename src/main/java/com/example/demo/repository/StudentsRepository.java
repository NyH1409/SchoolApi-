package com.example.demo.repository;

import com.example.demo.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentsRepository extends JpaRepository<Students, Long> {

  List<Students> findByNameContainingIgnoreCase(String query);
  Students findById_student(Long id);
}
