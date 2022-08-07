package com.example.demo.service.JDBCStudentsService;

import com.example.demo.model.Groups;
import com.example.demo.model.Students;
import com.example.demo.repository.JDBCRepository.JDBCStudentsRepository;
import com.example.demo.service.JDBCStudentsService.connection.DatabaseConnection;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class JDBCStudentsService extends DatabaseConnection implements JDBCStudentsRepository {

  public Students findById(Long id) throws SQLException, IOException, ClassNotFoundException {
    ResultSet result = DatabaseConnection.DBStatement().executeQuery("SELECT students.id_student, students.name, groups.id_group, groups.name AS group_name, groups.creation_date FROM students INNER JOIN groups ON students.id_group = groups.id_group WHERE students.id_students = "+id);
    Students student = new Students();
    while (result.next()){
      Long id_student = result.getLong("id_student");
      String name = result.getString("name");
      String group_name = result.getString("group_name");
      Long id_group = result.getLong("id_group");
      Date creation_date = result.getDate("creation_date");
      student.setId_student(id_student);
      student.setName(name);
      student.setGroup(new Groups(id_group, group_name, creation_date));
    }
    return student;
  }
  @Override
  public List<Students> findAll() throws SQLException, ClassNotFoundException, IOException {
    List<Students> studentList = new ArrayList<>();
    ResultSet res = DatabaseConnection.DBStatement().executeQuery("SELECT students.id_student, students.name, groups.id_group, groups.name AS group_name, groups.creation_date FROM students INNER JOIN groups ON students.id_group = groups.id_group");
    while(res.next()){
      Long id_student = res.getLong("id_student");
      String name = res.getString("name");
      String group_name = res.getString("group_name");
      Long id_group = res.getLong("id_group");
      Date creation_date = res.getDate("creation_date");
      studentList.add(new Students(id_student, name, new Groups(id_group, group_name, creation_date)));
    }
    return studentList;
  }

  @Override
  public Students save(Students student) throws SQLException, IOException, ClassNotFoundException {
    DatabaseConnection.DBStatement().executeUpdate("INSERT INTO students (name, id_group) VALUES ('"+student.getName()+"',"+student.getGroup().getId_group()+")");
    return findById(student.getId_student());
  }

  @Override
  public void deleteById(Long id) throws SQLException, IOException, ClassNotFoundException {
    DatabaseConnection.DBStatement().executeUpdate("DELETE FROM students WHERE id_student = "+id);
  }

  @Override
  public Students updateNameById(Long id, String newName) throws SQLException, IOException, ClassNotFoundException {
    DatabaseConnection.DBStatement().executeUpdate("UPDATE students SET name ="+newName+" WHERE id="+id);
    return findById(id);
  }

  @Override
  public List<Students> findWhereNameLike(String query) throws SQLException, IOException, ClassNotFoundException {
    ResultSet response = DatabaseConnection.DBStatement().executeQuery("SELECT students.id_student, students.name, groups.id_group, groups.name AS group_name, groups.creation_date FROM students INNER JOIN groups ON students.id_group = groups.id_group WHERE students.name LIKE CONCAT('%','"+query+"','%')");
    List<Students> studentList = new ArrayList<>();
    while(response.next()){
      Long id_student = response.getLong("id_student");
      String name = response.getString("name");
      String group_name = response.getString("group_name");
      Long id_group = response.getLong("id_group");
      Date creation_date = response.getDate("creation_date");
      studentList.add(new Students(id_student, name, new Groups(id_group, group_name, creation_date)));
    }
    return studentList;
  }
}
