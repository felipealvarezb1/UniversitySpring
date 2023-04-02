package felipe.springframework.university.mapper;

import felipe.springframework.university.common.dto.CareerDto;
import felipe.springframework.university.domain.model.Career;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CareerMapper {

    CareerDto modelToDto(Career career);

    List<CareerDto> modelListToDtoList(List<Career> careers);

    Career dtoModel(CareerDto careerDto);
}
