package felipe.springframework.university.domain.services;

import felipe.springframework.university.common.dto.SubjectDto;

import java.util.List;

public interface SubjectService{

    SubjectDto createSubject(SubjectDto subjectDto);

    SubjectDto updateSubject(SubjectDto subjectDto);

    SubjectDto findSubjectById(Long id);

    List<SubjectDto> findAllSubjects(int pageNumber, int pageSize);

    void deleteSubjectById(Long id);

}
