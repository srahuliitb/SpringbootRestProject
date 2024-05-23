package me.rahulsingh.springboot.rest.project.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfStudentEmailExists() {
        // given
        String email = "Doe.John@rahulsingh.me";
        LocalDate dob = LocalDate.of(1990, Month.MAY, 23);
        Student student = new Student("John Doe", email, dob);
        underTest.save(student);

        // when
        boolean expected = underTest.selectExistsEmail(email);

        // then
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckIfStudentEmailDoesNotExist() {
        // given
        String email = "Doe.John@rahulsingh.me";

        // when
        boolean expected = underTest.selectExistsEmail(email);

        // then
        assertThat(expected).isFalse();
    }

    @Test
    void shouldReturnStudentByEmail() {
        // given
        String email = "Doe.John@rahulsingh.me";
        LocalDate dob = LocalDate.of(1990, Month.MAY, 23);
        Student student = new Student("John Doe", email, dob);
        underTest.save(student);

        // when
        Optional<Student> expectedStudent = underTest.findStudentByEmail(email);

        // then
        assertThat(expectedStudent).isNotEmpty();
    }
}