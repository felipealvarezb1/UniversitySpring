package felipe.springframework.university.persistence;

import felipe.springframework.university.common.dto.GroupRegistrationDto;
import felipe.springframework.university.common.dto.GroupRegistrationGeneralDto;

public interface GroupRegistrationPersistence {

    GroupRegistrationGeneralDto registerStudent(Long studentId, Long groupId);

    GroupRegistrationDto getStudentGroupRegistration(Long studentId, Long groupId);
}
