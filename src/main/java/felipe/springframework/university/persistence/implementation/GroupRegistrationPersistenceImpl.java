package felipe.springframework.university.persistence.implementation;

import felipe.springframework.university.common.dto.GroupRegistrationDto;
import felipe.springframework.university.common.dto.GroupRegistrationGeneralDto;
import felipe.springframework.university.domain.model.Group;
import felipe.springframework.university.domain.model.GroupRegistration;
import felipe.springframework.university.domain.model.Student;
import felipe.springframework.university.mapper.GroupRegistrationMapper;
import felipe.springframework.university.persistence.GroupRegistrationPersistence;
import felipe.springframework.university.persistence.repository.GroupRegistrationRepository;
import felipe.springframework.university.persistence.repository.GroupRepository;
import felipe.springframework.university.persistence.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class GroupRegistrationPersistenceImpl implements GroupRegistrationPersistence {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final GroupRegistrationMapper groupRegistrationMapper;
    private final GroupRegistrationRepository groupRegistrationRepository;


    @Override
    public GroupRegistrationGeneralDto registerStudent(Long studentId, Long groupId) {
        final Student student = studentRepository.findStudentById(studentId);
        final Group group = groupRepository.findGroupById(groupId);

        GroupRegistration groupRegistration = new GroupRegistration();
        groupRegistration.setStudentId(student.getId());
        groupRegistration.setGroupId(group.getId());
        groupRegistration.setRegisteredAt(LocalDateTime.now());

        groupRegistrationRepository.save(groupRegistration);

        GroupRegistrationGeneralDto registerStudent = groupRegistrationMapper.modelToGeneralDto(groupRegistration);
        return registerStudent;
    }

    @Override
    public GroupRegistrationDto getStudentGroupRegistration(Long studentId, Long groupId) {
        GroupRegistration groupRegistration = groupRegistrationRepository.findByStudentIdAndGroupId(studentId, groupId);
        return groupRegistrationMapper.modelToDto(groupRegistration);
    }
}
