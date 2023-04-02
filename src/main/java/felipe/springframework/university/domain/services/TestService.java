package felipe.springframework.university.domain.services;

import felipe.springframework.university.common.dto.TestDto;

import java.util.List;

public interface TestService{

    TestDto createTest(TestDto testDto);

    TestDto updateTest(TestDto testDto);

    TestDto findTestById(Long id);

    List<TestDto> findAllTests();

    void deleteTestById(Long id);

}
