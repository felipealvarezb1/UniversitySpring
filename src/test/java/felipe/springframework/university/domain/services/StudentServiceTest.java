package felipe.springframework.university.domain.services;

import felipe.springframework.university.common.dto.GroupRegistrationDto;
import felipe.springframework.university.common.dto.GroupRegistrationGeneralDto;
import felipe.springframework.university.common.dto.StudentDto;
import felipe.springframework.university.domain.model.Student;
import felipe.springframework.university.domain.services.implementation.StudentServiceImpl;
import felipe.springframework.university.mapper.StudentMapper;
import felipe.springframework.university.persistence.GroupRegistrationPersistence;
import felipe.springframework.university.persistence.StudentPersistence;
import felipe.springframework.university.persistence.repository.StudentRepository;
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

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentPersistence studentPersistence;

    @Mock
    private GroupRegistrationPersistence registrationPersistence;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentServiceImpl studentService;

    private Student student;
    private StudentDto studentDto;

    @BeforeEach
    void init(){

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
    }

    @Test
    void createStudent() {
        when(studentPersistence.createStudent(studentDto)).thenReturn(studentDto);

        StudentDto createdStudent = studentService.createStudent(studentDto);

        Assertions.assertThat(createdStudent).isNotNull();
    }

    @Test
    void updateStudent() {
        when(studentRepository.save(student)).thenReturn(student);
        when(studentMapper.modelToDto(student)).thenReturn(studentDto);
        when(studentMapper.dtoToModel(studentDto)).thenReturn(student);

        StudentDto updatedStudent = studentService.updateStudent(studentDto);

        Assertions.assertThat(updatedStudent).isNotNull();
    }

    @Test
    void findStudentById() {
        Long id = 1L;

        when(studentRepository.findStudentById(id)).thenReturn(student);
        when(studentMapper.modelToDto(student)).thenReturn(studentDto);

        StudentDto foundStudent = studentService.findStudentById(id);

        Assertions.assertThat(foundStudent).isNotNull();

    }

    @Test
    void findAllStudents() {
        Page<Student> students = Mockito.mock(Page.class);

        when(studentRepository.findAll(any(Pageable.class))).thenReturn(students);

        List<StudentDto> savedStudents = studentService.findAllStudents(1, 10);

        Assertions.assertThat(savedStudents).isNotNull();
    }

    @Test
    void deleteStudentById() {
        Long id = 1L;

        when(studentRepository.findStudentById(id)).thenReturn(student);

        assertAll(() -> studentService.deleteStudentById(id));
    }

    @Test
    void registerStudent() {

        GroupRegistrationGeneralDto generalDto = new GroupRegistrationGeneralDto();
        generalDto.setStudentId(1L);
        generalDto.setGroupId(1L);
        generalDto.setRegisteredAt(LocalDateTime.now());

        when(registrationPersistence.registerStudent(generalDto.getStudentId(), generalDto.getGroupId())).thenReturn(generalDto);

        GroupRegistrationGeneralDto createdGeneralDto = studentService.registerStudent(generalDto);

        Assertions.assertThat(createdGeneralDto).isNotNull();
    }

    @Test
    void getStudentGroupRegistration() {
        GroupRegistrationGeneralDto generalDto = new GroupRegistrationGeneralDto();
        generalDto.setStudentId(1L);
        generalDto.setGroupId(1L);
        generalDto.setRegisteredAt(LocalDateTime.now());

        GroupRegistrationDto groupRegistrationDto = new GroupRegistrationDto();
        groupRegistrationDto.setFinalScore(4.6);
        groupRegistrationDto.setApproved(true);

        when(registrationPersistence.getStudentGroupRegistration(generalDto.getStudentId(), generalDto.getGroupId())).thenReturn(groupRegistrationDto);

        GroupRegistrationDto registrationGroup = studentService.getStudentGroupRegistration(generalDto.getStudentId(), generalDto.getGroupId());

        Assertions.assertThat(registrationGroup).isNotNull();
    }
}