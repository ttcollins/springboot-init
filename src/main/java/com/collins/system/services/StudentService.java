package com.collins.system.services;

import com.collins.system.exceptions.ValidationFailedException;
import com.collins.system.models.Student;
import com.collins.system.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) throws ValidationFailedException {
        if (studentRepository.findStudentByEmail(student.getEmail()).isPresent()) {
            throw new ValidationFailedException("Email Taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) throws ValidationFailedException {
        if (!studentRepository.existsById(studentId))
            throw new ValidationFailedException("Student with Id: " + studentId + " does not exist");
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) throws ValidationFailedException {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new ValidationFailedException("Student with Id: " + studentId +
                        " does not exist")
        );

        if (name != null && name.length() > 0 && !name.equals(student.getName()))
            student.setName(name);

        if (email != null && email.length() > 0 && !email.equals(student.getEmail())) {
            if(studentRepository.findStudentByEmail(email).isPresent())
                throw new ValidationFailedException("Email taken");
            student.setEmail(email);
        }
    }
}
