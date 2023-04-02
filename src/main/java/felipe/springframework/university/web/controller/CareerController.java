package felipe.springframework.university.web.controller;

import felipe.springframework.university.common.dto.CareerDto;
import felipe.springframework.university.domain.services.CareerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CareerController {

    private final CareerService careerService;

    public CareerController(CareerService careerService) {
        this.careerService = careerService;
    }

    @PostMapping(path = "career", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CareerDto> saveCareer(@Valid @RequestBody CareerDto careerDto){
        return new ResponseEntity<>(careerService.createCareer(careerDto), HttpStatus.CREATED);
    }

    @PutMapping(path = "career", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CareerDto> updateCareer(@Valid @RequestBody CareerDto careerDto){
        return new ResponseEntity<>(careerService.updateCareer(careerDto), HttpStatus.OK);
    }

    @GetMapping(path = "career/{id}", produces = "application/json")
    public ResponseEntity<CareerDto> findCareerById(@PathVariable("id") Long id){
        return new ResponseEntity<>(careerService.findCareerById(id), HttpStatus.OK);
    }

    @GetMapping(path = "career", produces = "application/json")
    public ResponseEntity<List<CareerDto>> findAllCareer(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize){
        return new ResponseEntity<>(careerService.findAllCareers(pageNumber, pageSize), HttpStatus.OK);
    }

    @DeleteMapping(path = "career/{id}")
    public ResponseEntity<String> deleteCareerById(@PathVariable("id") Long id){
        careerService.deleteCareerById(id);
        return new ResponseEntity<>("Career deleted successfully", HttpStatus.OK);
    }

}
