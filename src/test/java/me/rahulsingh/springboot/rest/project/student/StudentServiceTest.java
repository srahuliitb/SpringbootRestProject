package me.rahulsingh.springboot.rest.project.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository; // It is already tested. So autowiring not required
    private StudentService underTest;

    @BeforeEach
    void setUp() {
        underTest = new StudentService(studentRepository);
    }

    @Test
    void shouldRetrieveAllStudents() {
        // when
        underTest.getAllStudents();
        // then
        verify(studentRepository).findAll();
    }

    @Test
    void shouldAddNewStudent() {
        // given
        String email = "Doe.John@rahulsingh.me";
        LocalDate dob = LocalDate.of(1990, Month.MAY, 23);
        Student student = new Student("John Doe", email, dob);

        // when
        underTest.addNewStudent(student);

        // then
        ArgumentCaptor<Student> argumentCaptorStudent = ArgumentCaptor.forClass(Student.class);

        verify(studentRepository).save(argumentCaptorStudent.capture());
        Student capturedValue = argumentCaptorStudent.getValue();
        assertThat(capturedValue).isEqualTo(student);
    }

    @Test
    void shouldThrowExceptionWhenEmailExists() {
        // given
        String email = "Doe.John@rahulsingh.me";
        LocalDate dob = LocalDate.of(1990, Month.MAY, 23);
        Student student = new Student("John Doe", email, dob);

        given(studentRepository.selectExistsEmail(student.getEmail()))
                .willReturn(true);

        // then
        assertThatThrownBy(() -> underTest.addNewStudent(student))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(String.format("Student with email %s already exists!", student.getEmail()));

        verify(studentRepository, never()).save(any());
    }

    @Test
    @Disabled
    void deleteStudent() {
    }
}