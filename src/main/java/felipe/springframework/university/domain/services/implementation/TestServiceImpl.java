package felipe.springframework.university.domain.services.implementation;

import felipe.springframework.university.common.dto.TestDto;
import felipe.springframework.university.domain.model.Test;
import felipe.springframework.university.domain.services.TestService;
import felipe.springframework.university.mapper.TestMapper;
import felipe.springframework.university.persistence.TestPersistence;
import felipe.springframework.university.persistence.repository.TestRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final TestMapper testMapper;
    private final TestPersistence testPersistence;

    @Override
    public TestDto createTest(TestDto testDto) {
        return testPersistence.createTest(testDto);
    }

    @Override
    public TestDto updateTest(TestDto testDto) {
        Test test = testRepository.save(testMapper.dtoToModel(testDto));
        TestDto updatedTestDto = testMapper.modelToDto(test);
        return updatedTestDto;
    }

    @Override
    public TestDto findTestById(Long id) {
        Test test = testRepository.findTestById(id);
        if (test == null){
            throw new EntityNotFoundException("the subject with id: "+id+" is not found");
        }
        TestDto findTestDto = testMapper.modelToDto(test);
        return findTestDto;
    }

    @Override
    public List<TestDto> findAllTests() {
        return testMapper.modelListToDtoList(testRepository.findAll());
    }

    @Override
    public void deleteTestById(Long id) {
        this.findTestById(id);
        testRepository.deleteById(id);
    }
}
