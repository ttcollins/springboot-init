package com.collins.system.config.system;

import com.collins.system.models.Role;
import com.collins.system.models.Student;
import com.collins.system.models.User;
import com.collins.system.repositories.StudentRepository;
import com.collins.system.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class SystemConfiguration {

    @Bean
    CommandLineRunner commandLineRunner1(StudentRepository studentRepository) {
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

    @Bean
    CommandLineRunner commandLineRunner2(UserService userService) {
        return  args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            userService.saveUser(new User(null, "John Travolta", "john", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Doki Golder", "doki", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Tamale Collins", "collins", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Trevor Angulo", "trevor", "1234", new ArrayList<>()));

            userService.addRoleToUser("john", "ROLE_USER");
            userService.addRoleToUser("john", "ROLE_MANAGER");
            userService.addRoleToUser("doki", "ROLE_MANAGER");
            userService.addRoleToUser("collins", "ROLE_ADMIN");
            userService.addRoleToUser("trevor", "ROLE_SUPER_ADMIN");
            userService.addRoleToUser("trevor", "ROLE_ADMIN");
            userService.addRoleToUser("trevor", "ROLE_USER");
        };
    }

}
