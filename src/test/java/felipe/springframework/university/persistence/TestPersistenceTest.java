package felipe.springframework.university.persistence;

import felipe.springframework.university.common.dto.TestDto;
import felipe.springframework.university.domain.model.GroupRegistration;
import felipe.springframework.university.mapper.TestMapper;
import felipe.springframework.university.persistence.implementation.TestPersistenceImpl;
import felipe.springframework.university.persistence.repository.GroupRegistrationRepository;
import felipe.springframework.university.persistence.repository.TestRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestPersistenceTest {

    @Mock
    private TestRepository testRepository;

    @Mock
    private TestMapper testMapper;

    @Mock
    private GroupRegistrationRepository groupRegistrationRepository;

    @InjectMocks
    private TestPersistenceImpl testPersistence;

    private felipe.springframework.university.domain.model.Test test;
    private TestDto testDto;
    private GroupRegistration groupRegistration;

    @BeforeEach
    void setUp() {

        test = felipe.springframework.university.domain.model.Test.builder()
                .id(1L)
                .percentage(25)
                .name("Parical 1")
                .score(4.6)
                .registrationId(1L).build();

        testDto = TestDto.builder()
                .id(1L)
                .percentage(25)
                .name("Parical 1")
                .score(4.6)
                .registrationId(1L).build();
    }

    @Test
    void createTest() {

        Long id = 1L;

        when(testMapper.dtoToModel(testDto)).thenReturn(test);
        when(groupRegistrationRepository.findRegistrationById(id)).thenReturn(groupRegistration);
        when(testRepository.save(test)).thenReturn(test);
        when(testMapper.modelToDto(test)).thenReturn(testDto);

        TestDto createdTest = testPersistence.createTest(testDto);

        Assertions.assertThat(createdTest).isNotNull();
    }
}