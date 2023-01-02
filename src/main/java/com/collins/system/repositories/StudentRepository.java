package com.collins.system.repositories;

import com.collins.system.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

//    This will transform into an sql statement selecting a record from the db with a where statement
//    Optionally, you can define your own specific query that you would want to be executed with the method
//    @Query("SELECT s from Student s where s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

}
