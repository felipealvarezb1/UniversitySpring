package felipe.springframework.university.persistence;

import felipe.springframework.university.common.dto.GroupDto;
import felipe.springframework.university.common.dto.SubjectDto;
import felipe.springframework.university.domain.model.Group;
import felipe.springframework.university.domain.model.Subject;
import felipe.springframework.university.mapper.GroupMapper;
import felipe.springframework.university.persistence.implementation.GroupPersistenceImpl;
import felipe.springframework.university.persistence.repository.GroupRepository;
import felipe.springframework.university.persistence.repository.SubjectRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GroupPersistenceTest {

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private GroupMapper groupMapper;

    @InjectMocks
    private GroupPersistenceImpl groupPersistence;

    private Group group;
    private GroupDto groupDto;
    private Subject subject;
    private SubjectDto subjectDto;

    @BeforeEach
    void setUp() {

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

        subject = Subject.builder()
                .id(1L)
                .name("Programacion")
                .credits(3)
                .startDate(new Date(2022, 12, 12))
                .endDate(new Date(2023, 05, 12))
                .careerId(1L).build();

        subjectDto = SubjectDto.builder()
                .id(1L)
                .name("Programacion 2")
                .credits(4)
                .startDate(new Date(2022, 12, 12))
                .endDate(new Date(2023, 05, 12))
                .careerId(1L).build();
    }

    @Test
    void createGroup() {

        Long id = 1L;

        when(groupMapper.modelToDto(group)).thenReturn(groupDto);
        when(subjectRepository.findSubjectById(id)).thenReturn(subject);
        when(groupRepository.save(group)).thenReturn(group);
        when(groupMapper.dtoToModel(groupDto)).thenReturn(group);

        GroupDto createdGroup = groupPersistence.createGroup(groupDto);

        Assertions.assertThat(createdGroup).isNotNull();

    }
}