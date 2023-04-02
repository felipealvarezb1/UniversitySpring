package felipe.springframework.university.domain.services;

import felipe.springframework.university.common.dto.GroupDto;

import java.util.List;

public interface GroupService{

    GroupDto createGroup(GroupDto groupDto);

    GroupDto updateGroup(GroupDto groupDto);

    GroupDto findGroupById(Long id);

    List<GroupDto> findAllGroups(int pageNumber, int pageSize);

    void deleteGroupById(Long id);

}
