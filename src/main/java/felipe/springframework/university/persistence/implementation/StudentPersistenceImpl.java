package felipe.springframework.university.persistence.implementation;

import felipe.springframework.university.common.dto.StudentDto;
import felipe.springframework.university.domain.model.Student;
import felipe.springframework.university.mapper.StudentMapper;
import felipe.springframework.university.persistence.StudentPersistence;
import felipe.springframework.university.persistence.repository.CareerRepository;
import felipe.springframework.university.persistence.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentPersistenceImpl implements StudentPersistence{

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final CareerRepository careerRepository;

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = studentMapper.dtoToModel(studentDto);
        student.setCareer(careerRepository.findCareerById(student.getCareerId()));

        Student savedStudent = studentRepository.save(student);

        StudentDto createdStudentDto = studentMapper.modelToDto(savedStudent);
        return createdStudentDto;
    }
}
