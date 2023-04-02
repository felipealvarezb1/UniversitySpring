package felipe.springframework.university.domain.services;

import felipe.springframework.university.common.dto.GroupDto;
import felipe.springframework.university.common.dto.SubjectDto;
import felipe.springframework.university.domain.model.Group;
import felipe.springframework.university.domain.model.Subject;
import felipe.springframework.university.domain.services.implementation.GroupServiceImpl;
import felipe.springframework.university.mapper.GroupMapper;
import felipe.springframework.university.persistence.GroupPersistence;
import felipe.springframework.university.persistence.repository.GroupRepository;
import felipe.springframework.university.persistence.repository.SubjectRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GroupServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private GroupPersistence groupPersistence;

    @Mock
    private GroupMapper groupMapper;

    @InjectMocks
    private GroupServiceImpl groupService;


    private Group group;
    private GroupDto groupDto;

    @BeforeEach
    void init(){

        group = Group.builder()
                .id(1L)
                .name("STM231")
                .schedule("J/9:00-12:00pm")
                .room("33-105")
                .teacher("Felipe")
                .subjectId(1L).build();

        groupDto = GroupDto.builder()
                .id(1L)
                .name("STM231")
                .schedule("J/9:00-12:00pm")
                .room("33-105")
                .teacher("Felipe")
                .subjectId(1L).build();

    }

    @Test
    void createGroup() {
        when(groupPersistence.createGroup(groupDto)).thenReturn(groupDto);

        GroupDto createdGroup = groupService.createGroup(groupDto);

        Assertions.assertThat(createdGroup).isNotNull();
    }

    @Test
    void updateGroup() {

        when(groupRepository.save(group)).thenReturn(group);
        when(groupMapper.modelToDto(group)).thenReturn(groupDto);
        when(groupMapper.dtoToModel(groupDto)).thenReturn(group);

        GroupDto updatedGroup = groupService.updateGroup(groupDto);

        Assertions.assertThat(updatedGroup).isNotNull();
    }

    @Test
    void findGroupById() {
        Long id = 1L;

        when(groupRepository.findGroupById(id)).thenReturn(group);
        when(groupMapper.modelToDto(group)).thenReturn(groupDto);

        GroupDto foundGroup = groupService.findGroupById(id);

        Assertions.assertThat(foundGroup).isNotNull();
    }

    @Test
    void findAllGroups() {
        Page<Group> groups = Mockito.mock(Page.class);

        when(groupRepository.findAll(any(Pageable.class))).thenReturn(groups);

        List<GroupDto> savedGroup = groupService.findAllGroups(1, 10);

        Assertions.assertThat(savedGroup).isNotNull();

    }

    @Test
    void deleteGroupById() {
        when(groupRepository.findGroupById(1L)).thenReturn(group);
        assertAll(() -> groupService.deleteGroupById(1L));
    }
}