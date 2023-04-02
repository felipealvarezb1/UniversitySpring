package felipe.springframework.university.domain.services;

import felipe.springframework.university.common.dto.CareerDto;
import felipe.springframework.university.common.dto.SubjectDto;
import felipe.springframework.university.domain.model.Career;
import felipe.springframework.university.domain.model.Subject;
import felipe.springframework.university.domain.services.implementation.SubjectServiceImpl;
import felipe.springframework.university.mapper.SubjectMapper;
import felipe.springframework.university.persistence.SubjectPersistence;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubjectServiceTest {

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private SubjectMapper subjectMapper;

    @Mock
    private SubjectPersistence subjectPersistence;

    @InjectMocks
    private SubjectServiceImpl subjectService;

    private SubjectDto subjectDto;
    private Subject subject;

    @BeforeEach
    void init(){

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
    void createSubject() {
        when(subjectPersistence.createSubject(subjectDto)).thenReturn(subjectDto);

        SubjectDto createdSubject = subjectService.createSubject(subjectDto);

        Assertions.assertThat(createdSubject).isNotNull();

    }

    @Test
    void updateSubject() {

        when(subjectRepository.save(subject)).thenReturn(subject);
        when(subjectMapper.modelToDto(subject)).thenReturn(subjectDto);
        when(subjectMapper.dtoToModel(subjectDto)).thenReturn(subject);

        SubjectDto updatedSubject = subjectService.updateSubject(subjectDto);

        Assertions.assertThat(updatedSubject).isNotNull();
    }

    @Test
    void findSubjectById() {
        when(subjectRepository.findSubjectById(1L)).thenReturn(subject);
        when(subjectMapper.modelToDto(subject)).thenReturn(subjectDto);

        SubjectDto foundSubject = subjectService.findSubjectById(1L);

        Assertions.assertThat(foundSubject).isNotNull();
    }

    @Test
    void findAllSubjects() {
        Page<Subject> subjects = Mockito.mock(Page.class);

        when(subjectRepository.findAll(Mockito.any(Pageable.class))).thenReturn(subjects);

        List<SubjectDto> savedSubject = subjectService.findAllSubjects(1, 10);

        Assertions.assertThat(savedSubject).isNotNull();
    }

    @Test
    void deleteSubjectById() {
        when(subjectRepository.findSubjectById(1L)).thenReturn(subject);
        assertAll(() -> subjectService.deleteSubjectById(1L));
    }
}