package com.example.databaseex;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    private final StudentRepository repository;

    StudentController (StudentRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/students")
    public void createStudent(@RequestBody Student student) {
        repository.save(student);
    }

    @GetMapping("/students")
    public List<Student> getAllStudent() {
        return repository.findAll();
    }

    @PatchMapping("/students/{id}")
    public Student updateStudent(Student updatedStudent, @PathVariable Long id) {
        Optional<Student> optionalStudent = repository.findById(id);
        Student student = null;

        if (optionalStudent.isEmpty()) return null;
        student=optionalStudent.get();

        if (updatedStudent.getName() != null) {
            student.setName(updatedStudent.getName());
        }
        if (updatedStudent.getGrade() != null) {
            student.setGrade(updatedStudent.getGrade());
        }

        repository.save(student);

        return student;
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
