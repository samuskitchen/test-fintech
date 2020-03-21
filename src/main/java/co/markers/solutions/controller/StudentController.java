package co.markers.solutions.controller;

import co.markers.solutions.domain.assemblers.StudentResponseAssembler;
import co.markers.solutions.domain.dtos.request.StudentRequest;
import co.markers.solutions.domain.dtos.response.StudentResponse;
import co.markers.solutions.domain.entity.StudentEntity;
import co.markers.solutions.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
@Api(tags = "Students")
public class StudentController {

    private static final ModelMapper modelMapper = new ModelMapper();

    private final StudentService studentService;
    private final StudentResponseAssembler studentResponseAssembler;

    @Autowired
    public StudentController(StudentService studentService, StudentResponseAssembler studentResponseAssembler) {
        this.studentService = studentService;
        this.studentResponseAssembler = studentResponseAssembler;
    }

    @PostMapping(path = "/insert")
    @ApiOperation(value = "Insert Student", response = StudentEntity.class)
    public StudentEntity insertStudent(@RequestBody StudentRequest studentRequest) {
        StudentEntity student = modelMapper.map(studentRequest, StudentEntity.class);
        return studentService.saveStudent(student);
    }

    @PutMapping(path = "/update")
    @ApiOperation(value = "Update Student", response = StudentEntity.class)
    public StudentEntity updateStudent(@RequestBody StudentRequest studentRequest) {
        StudentEntity student = modelMapper.map(studentRequest, StudentEntity.class);
        return studentService.updateStudent(student);
    }

    @GetMapping(path = "", produces = { "application/hal+json" })
    @ApiOperation(value = "Find All Student", response = StudentEntity.class)
    public ResponseEntity<CollectionModel<StudentResponse>> getAllStudent() {
        List<StudentEntity> studentEntities = studentService.findAllStudent();

        return new ResponseEntity<>(
                studentResponseAssembler.toCollectionModel(studentEntities), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Find Student By Id", response = StudentResponse.class)
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable Long id) {
        return studentService.findStudentById(id)
                .map(studentResponseAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/delete")
    @ApiOperation(value = "Delete Student By Id", response = StudentEntity.class)
    public void removePatient(@RequestParam Long id) {
        studentService.deleteStudentById(id);
    }

}
