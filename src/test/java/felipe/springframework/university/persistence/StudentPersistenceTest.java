package felipe.springframework.university.persistence;

import felipe.springframework.university.common.dto.CareerDto;
import felipe.springframework.university.common.dto.StudentDto;
import felipe.springframework.university.domain.model.Career;
import felipe.springframework.university.domain.model.Student;
import felipe.springframework.university.mapper.StudentMapper;
import felipe.springframework.university.persistence.implementation.StudentPersistenceImpl;
import felipe.springframework.university.persistence.repository.CareerRepository;
import felipe.springframework.university.persistence.repository.StudentRepository;
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
class StudentPersistenceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @Mock
    private CareerRepository careerRepository;

    @InjectMocks
    private StudentPersistenceImpl studentPersistence;


    private Student student;
    private StudentDto studentDto;
    private CareerDto careerDto;
    private Career career;

    @BeforeEach
    void setUp() {

        student = Student.builder()
                .id(1L)
                .firstName("Felipe")
                .lastName("Alvarez")
                .email("f@hotmail.com")
                .username("falvarez")
                .careerId(1L).build();

        studentDto = StudentDto.builder()
                .id(1L)
                .firstName("Felipe")
                .lastName("Alvarez")
                .email("f@hotmail.com")
                .username("falvarez")
                .careerId(1L).build();

        career = Career.builder()
                .id(1L)
                .name("Ingenieria de sistemas")
                .description("Software")
                .totalCredits(160).build();

        careerDto = CareerDto.builder()
                .id(1L)
                .name("Ingenieria de sistemas")
                .description("Software")
                .totalCredits(160).build();

    }

    @Test
    void createStudent() {

        Long id = 1L;

        when(studentMapper.dtoToModel(studentDto)).thenReturn(student);
        when(careerRepository.findCareerById(id)).thenReturn(career);
        when(studentRepository.save(student)).thenReturn(student);
        when(studentMapper.modelToDto(student)).thenReturn(studentDto);

        StudentDto createdStudent = studentPersistence.createStudent(studentDto);

        Assertions.assertThat(createdStudent).isNotNull();
    }
}