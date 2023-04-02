package felipe.springframework.university.domain.services;

import felipe.springframework.university.common.dto.GroupRegistrationDto;

public interface GroupRegistrationService {

    void deleteRegistrationById(Long id);

    GroupRegistrationDto findRegistrationById(Long id);
}
