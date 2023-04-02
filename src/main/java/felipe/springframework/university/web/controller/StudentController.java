package felipe.springframework.university.web.controller;

import felipe.springframework.university.common.dto.GroupRegistrationDto;
import felipe.springframework.university.common.dto.GroupRegistrationGeneralDto;
import felipe.springframework.university.common.dto.StudentDto;
import felipe.springframework.university.domain.services.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<StudentDto> createStudent(@Valid @RequestBody StudentDto studentDto){
        return new ResponseEntity<>(studentService.createStudent(studentDto), HttpStatus.CREATED);
    }

    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<GroupRegistrationGeneralDto> registerStudent(@RequestBody GroupRegistrationGeneralDto groupRegistrationGeneralDto){
        return new ResponseEntity<>(studentService.registerStudent(groupRegistrationGeneralDto), HttpStatus.OK);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<StudentDto> updateStudent(@Valid @RequestBody StudentDto studentDto){
        return new ResponseEntity<>(studentService.updateStudent(studentDto), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<StudentDto> findStudentById(@PathVariable Long id){
        return new ResponseEntity<>(studentService.findStudentById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/{studentId}/group/{groupId}", produces = "application/json")
    public ResponseEntity<GroupRegistrationDto> getStudentGroupRegistration(@PathVariable("studentId") Long studentId,
                                                                            @PathVariable("groupId") Long groupId){
        return new ResponseEntity<>(studentService.getStudentGroupRegistration(studentId, groupId), HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<StudentDto>> findAllStudents(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize){
        return new ResponseEntity<>(studentService.findAllStudents(pageNumber, pageSize), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
    }
}
