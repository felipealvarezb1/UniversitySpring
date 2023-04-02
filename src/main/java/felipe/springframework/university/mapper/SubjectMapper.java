package felipe.springframework.university.mapper;

import felipe.springframework.university.common.dto.SubjectDto;
import felipe.springframework.university.domain.model.Subject;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SubjectMapper {

    SubjectDto modelToDto(Subject subject);

    List<SubjectDto> modelListToDtoList(List<Subject> subjects);

    Subject dtoToModel(SubjectDto subjectDto);
}
