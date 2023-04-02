package felipe.springframework.university.domain.services.implementation;

import felipe.springframework.university.common.dto.SubjectDto;
import felipe.springframework.university.domain.model.Subject;
import felipe.springframework.university.domain.services.SubjectService;
import felipe.springframework.university.mapper.SubjectMapper;
import felipe.springframework.university.persistence.SubjectPersistence;
import felipe.springframework.university.persistence.repository.SubjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;
    private final SubjectPersistence subjectPersistence;

    @Override
    public SubjectDto createSubject(SubjectDto subjectDto) {
        return subjectPersistence.createSubject(subjectDto);
    }

    @Override
    public SubjectDto updateSubject(SubjectDto subjectDto) {
        Subject subject = subjectRepository.save(subjectMapper.dtoToModel(subjectDto));
        SubjectDto updatedSubjectDto = subjectMapper.modelToDto(subject);
        return updatedSubjectDto;
    }

    @Override
    public SubjectDto findSubjectById(Long id) {
        Subject subject = subjectRepository.findSubjectById(id);
        if (subject == null){
            throw new EntityNotFoundException("the subject with id: "+id+" is not found");
        }
        SubjectDto subjectDto = subjectMapper.modelToDto(subject);
        return subjectDto;
    }

    @Override
    public List<SubjectDto> findAllSubjects(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Subject> subjects = subjectRepository.findAll(pageable);

        List<Subject> subjectList = subjects.getContent();
        return subjectList.stream().map(subject -> subjectMapper.modelToDto(subject)).collect(Collectors.toList());
    }

    @Override
    public void deleteSubjectById(Long id) {
        this.findSubjectById(id);
        subjectRepository.deleteById(id);
    }
}
