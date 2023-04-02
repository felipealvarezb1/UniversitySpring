package felipe.springframework.university.domain.services.implementation;

import com.sun.jdi.request.DuplicateRequestException;
import felipe.springframework.university.common.dto.GroupDto;
import felipe.springframework.university.domain.model.Group;
import felipe.springframework.university.domain.services.GroupService;
import felipe.springframework.university.mapper.GroupMapper;
import felipe.springframework.university.persistence.GroupPersistence;
import felipe.springframework.university.persistence.repository.GroupRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final GroupPersistence groupPersistence;

    private void groupExistsByName(String name){
        if (groupRepository.existsByName(name)){
            throw new DuplicateRequestException("the group is already registered");
        }
    }

    @Override
    public GroupDto createGroup(GroupDto groupDto) {
        this.groupExistsByName(groupDto.getName());
        return groupPersistence.createGroup(groupDto);
    }

    @Override
    public GroupDto updateGroup(GroupDto groupDto) {
        Group group = groupRepository.save(groupMapper.dtoToModel(groupDto));
        GroupDto updatedGroupDto = groupMapper.modelToDto(group);
        return updatedGroupDto;
    }

    @Override
    public GroupDto findGroupById(Long id) {
        Group group = groupRepository.findGroupById(id);
        if(group == null){
            throw new EntityNotFoundException("the group with id: "+id+" is not found");
        }
        GroupDto groupDto = groupMapper.modelToDto(group);
        return groupDto;
    }

    @Override
    public List<GroupDto> findAllGroups(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Group> groups = groupRepository.findAll(pageable);

        List<Group> groupList = groups.getContent();
        return groupList.stream().map(group -> groupMapper.modelToDto(group)).collect(Collectors.toList());
    }

    @Override
    public void deleteGroupById(Long id) {
        this.findGroupById(id);
        groupRepository.deleteById(id);
    }
}
