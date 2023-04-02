package felipe.springframework.university.domain.services.implementation;

import felipe.springframework.university.common.dto.GroupRegistrationDto;
import felipe.springframework.university.domain.model.GroupRegistration;
import felipe.springframework.university.domain.services.GroupRegistrationService;
import felipe.springframework.university.mapper.GroupRegistrationMapper;
import felipe.springframework.university.persistence.repository.GroupRegistrationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GroupRegistrationServiceImpl implements GroupRegistrationService {

    private final GroupRegistrationRepository groupRegistrationRepository;
    private final GroupRegistrationMapper groupRegistrationMapper;

    @Override
    public GroupRegistrationDto findRegistrationById(Long id) {
        GroupRegistration groupRegistration = groupRegistrationRepository.findRegistrationById(id);
        if (groupRegistration == null){
            throw new EntityNotFoundException("the registration with id: "+id+" is not found");
        }
        GroupRegistrationDto registrationDto = groupRegistrationMapper.modelToDto(groupRegistration);
        return registrationDto;
    }

    @Override
    public void deleteRegistrationById(Long id) {
        this.findRegistrationById(id);
        groupRegistrationRepository.deleteById(id);
    }


}
