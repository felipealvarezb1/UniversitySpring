package felipe.springframework.university.persistence;

import felipe.springframework.university.common.dto.StudentDto;

public interface StudentPersistence {
    StudentDto createStudent(StudentDto studentDto);
}
