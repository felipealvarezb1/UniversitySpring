package felipe.springframework.university.persistence;

import felipe.springframework.university.common.dto.GroupDto;

public interface GroupPersistence {

    GroupDto createGroup(GroupDto groupDto);
}
