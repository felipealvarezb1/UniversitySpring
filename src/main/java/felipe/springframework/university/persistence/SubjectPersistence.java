package felipe.springframework.university.persistence;

import felipe.springframework.university.common.dto.SubjectDto;

public interface SubjectPersistence {

    SubjectDto createSubject(SubjectDto subjectDto);

}
