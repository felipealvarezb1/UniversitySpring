package felipe.springframework.university.mapper;

import felipe.springframework.university.common.dto.GroupDto;
import felipe.springframework.university.domain.model.Group;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface GroupMapper {

    GroupDto modelToDto(Group group);

    List<GroupDto> modelListToDtoList(List<Group> groups);

    Group dtoToModel(GroupDto groupDto);
}
