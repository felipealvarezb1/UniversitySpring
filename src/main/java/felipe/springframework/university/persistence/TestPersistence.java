package felipe.springframework.university.persistence;

import felipe.springframework.university.common.dto.TestDto;

public interface TestPersistence {

    TestDto createTest(TestDto testDto);
}
