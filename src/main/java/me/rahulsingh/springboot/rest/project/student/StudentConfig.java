package me.rahulsingh.springboot.rest.project.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student alice = new Student(
                "Alice Jones",
                "Jones.Alice@rahulsingh.me",
                LocalDate.of(1995, Month.MAY, 21)
            );

            Student songyun = new Student(
                    "Songyun Tan",
                    "Tan.Songyun@rahulsingh.me",
                    LocalDate.of(1990, Month.MAY, 30)
            );

            repository.saveAll(List.of(alice, songyun));
        };
    }
}
