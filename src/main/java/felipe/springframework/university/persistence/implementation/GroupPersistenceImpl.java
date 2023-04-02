package felipe.springframework.university.persistence.implementation;

import felipe.springframework.university.common.dto.GroupDto;
import felipe.springframework.university.domain.model.Group;
import felipe.springframework.university.mapper.GroupMapper;
import felipe.springframework.university.persistence.GroupPersistence;
import felipe.springframework.university.persistence.repository.GroupRepository;
import felipe.springframework.university.persistence.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupPersistenceImpl implements GroupPersistence {

    private final GroupRepository groupRepository;
    private final SubjectRepository subjectRepository;
    private final GroupMapper groupMapper;

    @Override
    public GroupDto createGroup(GroupDto groupDto) {
        Group group = groupMapper.dtoToModel(groupDto);
        group.setSubject(subjectRepository.findSubjectById(group.getSubjectId()));

        Group savedGroup = groupRepository.save(group);

        GroupDto savedGroupDto = groupMapper.modelToDto(savedGroup);
        return savedGroupDto;
    }
}
