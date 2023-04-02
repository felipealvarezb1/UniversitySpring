package felipe.springframework.university.persistence.implementation;

import felipe.springframework.university.common.dto.SubjectDto;
import felipe.springframework.university.domain.model.Subject;
import felipe.springframework.university.mapper.SubjectMapper;
import felipe.springframework.university.persistence.SubjectPersistence;
import felipe.springframework.university.persistence.repository.CareerRepository;
import felipe.springframework.university.persistence.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubjectPersistenceImpl implements SubjectPersistence {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;
    private final CareerRepository careerRepository;


    @Override
    public SubjectDto createSubject(SubjectDto subjectDto) {
        Subject subject = subjectMapper.dtoToModel(subjectDto);
        subject.setCareer(careerRepository.findCareerById(subject.getCareerId()));

        Subject savedSubject = subjectRepository.save(subject);

        SubjectDto createdSubjectDto = subjectMapper.modelToDto(savedSubject);
        return createdSubjectDto;
    }
}
