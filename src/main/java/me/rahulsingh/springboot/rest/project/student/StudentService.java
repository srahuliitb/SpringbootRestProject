package me.rahulsingh.springboot.rest.project.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentBeingSearched = studentRepository.findStudentByEmail(student.getEmail());
        if (studentBeingSearched.isPresent()) {
            throw new IllegalStateException("Email already exists!");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException(String.format("Student with id %s does not exists", studentId));
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new IllegalStateException(
                        String.format("Student with id %s does not exist", studentId)));

        if ((name != null) && (name.length() > 0) && (!Objects.equals(student.getName(), name))) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentUnderConsideration = studentRepository.findStudentByEmail(email);
            if (studentUnderConsideration.isPresent()) {
                throw new IllegalStateException("Email is already in use");
            }
            student.setEmail(email);
        }
    }
}
