package com.example.student.service;

import com.example.student.entity.Student;
import com.example.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Student addStudent(String name, int grade) {
        Student student = new Student(name, grade);
        studentRepository.add(student);

        return student;
    } //addStudent()

    public List<Student> getAll() {
        return studentRepository.getAll();
    } //getAll()

    public List<Student> getGradeStudent(int grade) {
        return studentRepository.get(grade);
    } //getGradeStudent()

} //class
