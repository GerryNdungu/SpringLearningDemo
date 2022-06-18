package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student gerald = new Student(
                   "Gerald",
                    "geraldgiteru@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 10)
            );
            Student geraldine = new Student(
                   "Geraldine",
                    "geraldine@gmail.com",
                    LocalDate.of(2001, Month.JANUARY, 10)
            );

            repository.saveAll(
                List.of(gerald, geraldine)
            );
        };
    }
}
