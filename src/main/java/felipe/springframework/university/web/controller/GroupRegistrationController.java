package felipe.springframework.university.web.controller;

import felipe.springframework.university.common.dto.GroupRegistrationDto;
import felipe.springframework.university.domain.services.GroupRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/registration")
public class GroupRegistrationController {

    private final GroupRegistrationService groupRegistrationService;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<GroupRegistrationDto> findRegistrationById(@PathVariable("id") Long id){
        return new ResponseEntity<>(groupRegistrationService.findRegistrationById(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteRegistrationById(@PathVariable("id") Long id){
        groupRegistrationService.deleteRegistrationById(id);
        return new ResponseEntity<>("registration deleted successfully", HttpStatus.OK);
    }
}
