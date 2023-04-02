package felipe.springframework.university.web.controller;

import felipe.springframework.university.common.dto.TestDto;
import felipe.springframework.university.domain.services.TestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<TestDto> createTest(@RequestBody TestDto testDto){
        return new ResponseEntity<>(testService.createTest(testDto), HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<TestDto> updatedTest(@RequestBody TestDto testDto){
        return new ResponseEntity<>(testService.updateTest(testDto), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<TestDto> findTestById(@PathVariable("id") Long id){
        return new ResponseEntity<>(testService.findTestById(id), HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<TestDto>> findAllTests(){
        return new ResponseEntity<>(testService.findAllTests(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteTestById(@PathVariable Long id){
        testService.deleteTestById(id);
        return new ResponseEntity<>("Test deleted successfully", HttpStatus.OK);
    }
}
