package felipe.springframework.university.domain.services;

import felipe.springframework.university.common.dto.CareerDto;

import java.util.List;

public interface CareerService{

    CareerDto createCareer(CareerDto careerDto);

    CareerDto updateCareer(CareerDto careerDto);

    CareerDto findCareerById(Long id);

    List<CareerDto> findAllCareers(int pageNumber, int pageSize);

    void deleteCareerById(Long id);

}
