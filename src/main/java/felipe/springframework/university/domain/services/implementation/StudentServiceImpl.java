package felipe.springframework.university.domain.services.implementation;

import com.sun.jdi.request.DuplicateRequestException;
import felipe.springframework.university.common.dto.GroupRegistrationDto;
import felipe.springframework.university.common.dto.GroupRegistrationGeneralDto;
import felipe.springframework.university.common.dto.StudentDto;
import felipe.springframework.university.domain.model.Student;
import felipe.springframework.university.domain.services.StudentService;
import felipe.springframework.university.mapper.StudentMapper;
import felipe.springframework.university.persistence.GroupRegistrationPersistence;
import felipe.springframework.university.persistence.StudentPersistence;
import felipe.springframework.university.persistence.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final StudentPersistence studentPersistence;
    private final GroupRegistrationPersistence groupRegistrationPersistence;

    private void studentUsernameExists(String username){
        if(studentRepository.existsByUsername(username)){
            throw new DuplicateRequestException("the username is already registered");
        }
    }

    private void studentEmailExists(String email){
        if (studentRepository.existsByEmail(email)){
            throw new DuplicateRequestException("the email is already registered");
        }
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        this.studentUsernameExists(studentDto.getUsername());
        this.studentEmailExists(studentDto.getEmail());
        return studentPersistence.createStudent(studentDto);
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto) {
        Student student = studentRepository.save(studentMapper.dtoToModel(studentDto));
        StudentDto updatedStudentDto = studentMapper.modelToDto(student);
        return updatedStudentDto;
    }

    @Override
    public StudentDto findStudentById(Long id) {
        Student student = studentRepository.findStudentById(id);
        if(student == null){
            throw new EntityNotFoundException("the student with id: "+id+" is not found");
        }
        StudentDto studentDto = studentMapper.modelToDto(student);
        return studentDto;
    }

    @Override
    public List<StudentDto> findAllStudents(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Student> students = studentRepository.findAll(pageable);

        List<Student> studentList = students.getContent();
        return studentList.stream().map(student -> studentMapper.modelToDto(student)).collect(Collectors.toList());
    }

    @Override
    public void deleteStudentById(Long id) {
        this.findStudentById(id);
        studentRepository.deleteById(id);
    }

    @Override
    public GroupRegistrationGeneralDto registerStudent(GroupRegistrationGeneralDto groupRegistrationGeneralDto) {

        return groupRegistrationPersistence.registerStudent(groupRegistrationGeneralDto.getStudentId(),
                groupRegistrationGeneralDto.getGroupId());
    }

    @Override
    public GroupRegistrationDto getStudentGroupRegistration(Long studentId, Long groupId) {
        return groupRegistrationPersistence.getStudentGroupRegistration(studentId, groupId);
    }


}
