package co.markers.solutions.domain.assemblers;

import co.markers.solutions.controller.StudentController;
import co.markers.solutions.domain.dtos.response.StudentResponse;
import co.markers.solutions.domain.entity.StudentEntity;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StudentResponseAssembler extends RepresentationModelAssemblerSupport<StudentEntity, StudentResponse> {

    public StudentResponseAssembler() {
        super(StudentController.class, StudentResponse.class);
    }


    @Override
    public StudentResponse toModel(StudentEntity entity) {

        StudentResponse studentResponse = instantiateModel(entity);

        studentResponse.add(linkTo(
                methodOn(StudentController.class)
                        .getStudentById(entity.getId())).withSelfRel());

        studentResponse.add(linkTo(
                methodOn(StudentController.class)
                        .getStudentById(entity.getId())).withRel("student"));

        studentResponse.setId(entity.getId());
        studentResponse.setFirstName(entity.getFirstName());
        studentResponse.setLastName(entity.getLastName());
        studentResponse.setEmail(entity.getEmail());

        return studentResponse;
    }

    @Override
    public CollectionModel<StudentResponse> toCollectionModel(Iterable<? extends StudentEntity> entities) {
        CollectionModel<StudentResponse> studentResponses = super.toCollectionModel(entities);

        studentResponses.add(linkTo(methodOn(StudentController.class).getAllStudent()).withSelfRel());
        studentResponses.add(linkTo(methodOn(StudentController.class).getAllStudent()).withRel("student"));

        return studentResponses;
    }

    public List<StudentResponse> toCollectionResponse(List<StudentEntity> entities) {

        if (entities.isEmpty())
            return Collections.emptyList();

        return entities.stream()
                .map(studentEntity -> StudentResponse.builder()
                        .id(studentEntity.getId())
                        .firstName(studentEntity.getFirstName())
                        .lastName(studentEntity.getLastName())
                        .email(studentEntity.getEmail())
                        .build()
                        .add(linkTo(
                                methodOn(StudentController.class)
                                        .getStudentById(studentEntity.getId()))
                                .withSelfRel()))
                .collect(Collectors.toList());

    }
}
