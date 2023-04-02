package felipe.springframework.university.domain.services;

import felipe.springframework.university.common.dto.CareerDto;
import felipe.springframework.university.domain.model.Career;
import felipe.springframework.university.domain.services.implementation.CareerServiceImpl;
import felipe.springframework.university.mapper.CareerMapper;
import felipe.springframework.university.persistence.repository.CareerRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.sql.SQLOutput;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CareerServiceTest {

    @Mock
    private CareerRepository careerRepository;

    @Mock
    private CareerMapper careerMapper;

    @InjectMocks
    private CareerServiceImpl careerService;


    private Career career;
    private CareerDto careerDto;

    @BeforeEach
    void init(){

         career = Career.builder()
                .id(1L)
                .name("Ingenieria de sistemas")
                .description("Software")
                .totalCredits(160).build();

         careerDto = CareerDto.builder()
                 .id(1L)
                 .name("Ingenieria de sistemas")
                 .description("Software")
                 .totalCredits(160).build();

    }

    @Test
    void createCareer() {

        when(careerRepository.save(Mockito.any(Career.class))).thenReturn(career);
        when(careerMapper.dtoModel(careerDto)).thenReturn(career);
        when(careerMapper.modelToDto(career)).thenReturn(careerDto);

        CareerDto savedCareer = careerService.createCareer(careerDto);

        Assertions.assertThat(savedCareer).isNotNull();

    }

    @Test
    void updateCareer() {

        when(careerRepository.save(Mockito.any(Career.class))).thenReturn(career);
        when(careerMapper.dtoModel(careerDto)).thenReturn(career);
        when(careerMapper.modelToDto(career)).thenReturn(careerDto);


        CareerDto savedCareer = careerService.updateCareer(careerDto);

        Assertions.assertThat(savedCareer).isNotNull();
    }

    @Test
    void findCareerById() {
        Long id = 1L;

        when(careerRepository.findCareerById(id)).thenReturn(career);
        when(careerMapper.modelToDto(career)).thenReturn(careerDto);

        CareerDto foundCareer = careerService.findCareerById(id);

        Assertions.assertThat(foundCareer).isNotNull();
    }

    @Test
    void findAllCareers() {
        Page<Career> careers = Mockito.mock(Page.class);

        when(careerRepository.findAll(Mockito.any(Pageable.class))).thenReturn(careers);

        List<CareerDto> savedCareer = careerService.findAllCareers(1, 10);
        Assertions.assertThat(savedCareer).isNotNull();
    }

    @Test
    void deleteCareerById() {
        Long id = 1L;

        when(careerRepository.findCareerById(id)).thenReturn(career);

        assertAll(() -> careerService.deleteCareerById(id));
    }
}