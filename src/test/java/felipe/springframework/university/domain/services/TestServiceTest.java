package felipe.springframework.university.domain.services;

import felipe.springframework.university.common.dto.TestDto;
import felipe.springframework.university.domain.services.implementation.TestServiceImpl;
import felipe.springframework.university.mapper.TestMapper;
import felipe.springframework.university.persistence.TestPersistence;
import felipe.springframework.university.persistence.repository.TestRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestServiceTest {

    @Mock
    private TestRepository testRepository;

    @Mock
    private TestMapper testMapper;

    @Mock
    private TestPersistence testPersistence;

    @InjectMocks
    private TestServiceImpl testService;

    private felipe.springframework.university.domain.model.Test test;
    private TestDto testDto;


    @BeforeEach
    void setUp(){

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
        when(testPersistence.createTest(testDto)).thenReturn(testDto);

        TestDto createdTest = testService.createTest(testDto);

        Assertions.assertThat(createdTest).isNotNull();
    }

    @Test
    void updateTest() {
        when(testRepository.save(test)).thenReturn(test);
        when(testMapper.modelToDto(test)).thenReturn(testDto);
        when(testMapper.dtoToModel(testDto)).thenReturn(test);

        TestDto updatedTest = testService.updateTest(testDto);

        Assertions.assertThat(updatedTest).isNotNull();
    }

    @Test
    void findTestById() {
        Long id = 1L;

        when(testRepository.findTestById(id)).thenReturn(test);
        when(testMapper.modelToDto(test)).thenReturn(testDto);

        TestDto foundTest = testService.findTestById(id);

        Assertions.assertThat(foundTest).isNotNull();
    }

    @Test
    void findAllTests() {
        List<felipe.springframework.university.domain.model.Test> tests = new ArrayList<>();
        tests.add(test);

        when(testRepository.findAll()).thenReturn(tests);

        List<TestDto> savedTest = testService.findAllTests();

        Assertions.assertThat(savedTest).isNotNull();
    }

    @Test
    void deleteTestById() {
        Long id = 1L;

        when(testRepository.findTestById(id)).thenReturn(test);

        assertAll(()-> testService.deleteTestById(id));
    }
}