package felipe.springframework.university.mapper;

import felipe.springframework.university.common.dto.StudentDto;
import felipe.springframework.university.domain.model.Student;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface StudentMapper {

    StudentDto modelToDto(Student student);

    List<StudentDto> modelListToDtoList(List<Student> students);

    Student dtoToModel(StudentDto studentDto);
}
