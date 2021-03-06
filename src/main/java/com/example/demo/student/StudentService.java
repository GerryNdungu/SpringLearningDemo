package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
@Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.
                findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw  new IllegalStateException("Email Already Exists");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        checkStudentExists(studentId);
        studentRepository.deleteById(studentId);

//        studentRepository.delete(studentId);
    }

    private void checkStudentExists(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists){
            throw new IllegalStateException(
                    "student with id "+ studentId + " does not exists");
        }
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new IllegalStateException(
                        "student with id " + studentId + " does not exist"
                ));

                if (name != null && name.length()>0 && !Objects.equals(student.getName(), name)){
                    student.setName(name);
                }
                if (email != null && email.length()>0 && !Objects.equals(student.getEmail(), email)){
                    Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
                    if (studentOptional.isPresent()){
                        throw new IllegalStateException("email taken");
                    }
                    student.setEmail(email);
                }
//             checkStudentExists(studentId);
//             Student saved_student = studentRepository.findById(studentId).get();
//        saved_student.setName(student.getName());
//        saved_student.setEmail(student.getEmail());

//             studentRepository.save(saved_student);
    }
}
