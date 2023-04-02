package felipe.springframework.university.persistence.implementation;

import felipe.springframework.university.common.dto.TestDto;
import felipe.springframework.university.domain.model.Test;
import felipe.springframework.university.mapper.TestMapper;
import felipe.springframework.university.persistence.TestPersistence;
import felipe.springframework.university.persistence.repository.GroupRegistrationRepository;
import felipe.springframework.university.persistence.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestPersistenceImpl implements TestPersistence {

    private final TestRepository testRepository;
    private final TestMapper testMapper;
    private final GroupRegistrationRepository groupRegistrationRepository;

    @Override
    public TestDto createTest(TestDto testDto) {
        Test test = testMapper.dtoToModel(testDto);
        test.setRegistration(groupRegistrationRepository.findRegistrationById(test.getRegistrationId()));

        Test savedTest = testRepository.save(test);

        TestDto createdTestDto = testMapper.modelToDto(savedTest);
        return createdTestDto;
    }
}
