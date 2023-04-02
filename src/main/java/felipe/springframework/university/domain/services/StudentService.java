package felipe.springframework.university.domain.services;

import felipe.springframework.university.common.dto.GroupRegistrationDto;
import felipe.springframework.university.common.dto.GroupRegistrationGeneralDto;
import felipe.springframework.university.common.dto.StudentDto;

import java.util.List;

public interface StudentService {

    StudentDto createStudent(StudentDto studentDto);

    StudentDto updateStudent(StudentDto studentDto);

    StudentDto findStudentById(Long id);

    List<StudentDto> findAllStudents(int pageNumber, int pageSize);

    void deleteStudentById(Long id);

    GroupRegistrationGeneralDto registerStudent(GroupRegistrationGeneralDto groupRegistrationGeneralDto);

    GroupRegistrationDto getStudentGroupRegistration(Long studentId, Long groupId);


}
