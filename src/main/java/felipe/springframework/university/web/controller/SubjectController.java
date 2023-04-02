package felipe.springframework.university.web.controller;

import felipe.springframework.university.common.dto.SubjectDto;
import felipe.springframework.university.domain.services.SubjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<SubjectDto> createSubject(@Valid @RequestBody SubjectDto subjectDto){
        return new ResponseEntity<>(subjectService.createSubject(subjectDto), HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<SubjectDto> updateSubject(@Valid @RequestBody SubjectDto subjectDto){
        return new ResponseEntity<>(subjectService.updateSubject(subjectDto), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<SubjectDto> findSubjectById(@PathVariable("id") Long id){
        return new ResponseEntity<>(subjectService.findSubjectById(id), HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<SubjectDto>> findAllSubject(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize){
        return new ResponseEntity<>(subjectService.findAllSubjects(pageNumber, pageSize), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteSubjectById(@PathVariable("id") Long id){
        subjectService.deleteSubjectById(id);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }
}
