package felipe.springframework.university.mapper;


import felipe.springframework.university.common.dto.TestDto;
import felipe.springframework.university.domain.model.Test;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TestMapper {

    TestDto modelToDto(Test test);

    List<TestDto> modelListToDtoList(List<Test> tests);

    Test dtoToModel(TestDto testDto);
}
