package co.markers.solutions.service;

import co.markers.solutions.domain.entity.StudentEntity;
import co.markers.solutions.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentEntity saveStudent(StudentEntity student){
        return studentRepository.save(student);
    }

    public StudentEntity updateStudent(StudentEntity student){
        return studentRepository.save(student);
    }

    public List<StudentEntity> findAllStudent(){
        return studentRepository.findAll();
    }

    public Optional<StudentEntity> findStudentById(Long id){
        return studentRepository.findById(id);
    }

    public void deleteStudentById(Long id){
        studentRepository.deleteById(id);
    }
}
