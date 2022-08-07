package com.example.demo.repository.JPQLrepository;

import com.example.demo.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface JPQLStudentsRepository extends JpaRepository<Students,Long> {
  @Query("select s from Students s")
  List<Students> findAll();

  @Query(value = "insert into Students values :student", nativeQuery = true)
  Students save(@Param("student") Students student);

  @Query("delete from Students s where s.id_student = :id")
  void deleteById(@Param("id") Long id);

  @Query("update Students s set s.name = :newName where s.id_student = :id")
  Students updateNameById(
          @Param("id") Long id,
          @Param("newName") String newName);
  @Query("select s from Students s where lower(s.name) like lower(concat('%',:query,'%')) ")
  List<Students> findWhereNameLike(@Param("query") String query);
}
