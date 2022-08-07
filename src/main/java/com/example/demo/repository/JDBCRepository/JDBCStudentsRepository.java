package com.example.demo.repository.JDBCRepository;

import com.example.demo.model.Students;
import org.springframework.stereotype.Repository;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Repository
public interface JDBCStudentsRepository {
  List<Students> findAll() throws SQLException, ClassNotFoundException, IOException;

  Students save(Students student) throws SQLException, IOException, ClassNotFoundException;

  void deleteById(Long id) throws SQLException, IOException, ClassNotFoundException;

  Students updateNameById(Long id, String newName) throws SQLException, IOException, ClassNotFoundException;

  List<Students> findWhereNameLike(String query) throws SQLException, IOException, ClassNotFoundException;
}
