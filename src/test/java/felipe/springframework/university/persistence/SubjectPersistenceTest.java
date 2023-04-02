package felipe.springframework.university.persistence;

import felipe.springframework.university.common.dto.CareerDto;
import felipe.springframework.university.common.dto.SubjectDto;
import felipe.springframework.university.domain.model.Career;
import felipe.springframework.university.domain.model.Subject;
import felipe.springframework.university.mapper.SubjectMapper;
import felipe.springframework.university.persistence.implementation.SubjectPersistenceImpl;
import felipe.springframework.university.persistence.repository.CareerRepository;
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
class SubjectPersistenceTest {

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private SubjectMapper subjectMapper;

    @Mock
    private CareerRepository careerRepository;

    @InjectMocks
    private SubjectPersistenceImpl subjectPersistence;

    private Subject subject;
    private SubjectDto subjectDto;
    private Career career;
    private CareerDto careerDto;

    @BeforeEach
    void setUp() {

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

        career = Career.builder()
                .name("Ingenieria de sistemas")
                .description("Software")
                .totalCredits(160).build();

        careerDto = CareerDto.builder()
                .name("Ingenieria de sistemas")
                .description("Software")
                .totalCredits(160).build();
    }

    @Test
    void createSubject() {

        Long id = 1L;

        when(subjectMapper.dtoToModel(subjectDto)).thenReturn(subject);
        when(careerRepository.findCareerById(id)).thenReturn(career);
        when(subjectRepository.save(subject)).thenReturn(subject);
        when(subjectMapper.modelToDto(subject)).thenReturn(subjectDto);

        SubjectDto createdSubject = subjectPersistence.createSubject(subjectDto);

        Assertions.assertThat(createdSubject).isNotNull();
    }
}