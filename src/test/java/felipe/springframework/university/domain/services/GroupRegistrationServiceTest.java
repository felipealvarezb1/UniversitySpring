package felipe.springframework.university.domain.services;


import felipe.springframework.university.common.dto.GroupRegistrationDto;
import felipe.springframework.university.domain.model.GroupRegistration;
import felipe.springframework.university.domain.services.implementation.GroupRegistrationServiceImpl;
import felipe.springframework.university.mapper.GroupRegistrationMapper;
import felipe.springframework.university.persistence.GroupRegistrationPersistence;
import felipe.springframework.university.persistence.repository.GroupRegistrationRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GroupRegistrationServiceTest {

    @Mock
    private GroupRegistrationRepository groupRegistrationRepository;

    @Mock
    private GroupRegistrationMapper groupRegistrationMapper;

    @InjectMocks
    private GroupRegistrationServiceImpl groupRegistrationService;


    private GroupRegistration groupRegistration;
    private felipe.springframework.university.domain.model.Test test;

    List<felipe.springframework.university.domain.model.Test> testList = new ArrayList<>();

    @BeforeEach
    void setUp(){

        test = felipe.springframework.university.domain.model.Test.builder()
                .id(1L)
                .percentage(25)
                .name("Parical 1")
                .score(4.6)
                .registrationId(1L).build();

        testList.add(test);

        groupRegistration = GroupRegistration.builder()
                .id(1L)
                .studentId(1L)
                .groupId(1L)
                .registeredAt(LocalDateTime.now())
                .tests(testList)
                .build();
    }

    @Test
    void deleteRegistrationById() {
        Long id = 1L;

        when(groupRegistrationRepository.findRegistrationById(id)).thenReturn(groupRegistration);

        assertAll(() -> groupRegistrationService.deleteRegistrationById(id));
    }

    @Test
    void findRegistrationById() {
        Long id = 1L;

        GroupRegistrationDto groupRegistrationDto = new GroupRegistrationDto();
        groupRegistrationDto.setTests(testList);
        groupRegistrationDto.setFinalScore(4.6);
        groupRegistrationDto.setApproved(true);

        when(groupRegistrationRepository.findRegistrationById(id)).thenReturn(groupRegistration);
        when(groupRegistrationMapper.modelToDto(groupRegistration)).thenReturn(groupRegistrationDto);

        GroupRegistrationDto foundGroupRegistrationDto = groupRegistrationService.findRegistrationById(id);

        Assertions.assertThat(foundGroupRegistrationDto).isNotNull();

    }
}