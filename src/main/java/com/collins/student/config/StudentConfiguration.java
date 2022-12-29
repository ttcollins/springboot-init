package com.collins.student.config;

import com.collins.student.models.Student;
import com.collins.student.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfiguration {

    @Bean
    CommandLineRunner commandLineRunner (StudentRepository studentRepository) {
        return  args -> {
            Student collins = new Student(
                    "Collins",
                    "collins@gmail.com",
                    LocalDate.of(1999, Month.APRIL, 14)
            );

            Student doki = new Student(
                    "Doki",
                    "doki@gmail.com",
                    LocalDate.of(1998, Month.NOVEMBER, 12)
            );

            studentRepository.saveAll(List.of(collins, doki));
        };
    }

}
