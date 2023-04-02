package felipe.springframework.university.domain.services.implementation;

import com.sun.jdi.request.DuplicateRequestException;
import felipe.springframework.university.common.dto.CareerDto;
import felipe.springframework.university.domain.model.Career;
import felipe.springframework.university.domain.services.CareerService;
import felipe.springframework.university.mapper.CareerMapper;
import felipe.springframework.university.persistence.repository.CareerRepository;
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
public class CareerServiceImpl implements CareerService {

    private final CareerRepository careerRepository;
    private final CareerMapper careerMapper;

    private void careerExistsByName(String name){
        if (careerRepository.existsByName(name)){
            throw new DuplicateRequestException("the career is already registered");
        }
    }

    @Override
    public CareerDto createCareer(CareerDto careerDto) {
        Career career = careerMapper.dtoModel(careerDto);
        this.careerExistsByName(career.getName());

        Career savedCareer = careerRepository.save(career);

        CareerDto savedCareerDto = careerMapper.modelToDto(savedCareer);
        return savedCareerDto;

    }

    @Override
    public CareerDto updateCareer(CareerDto careerDto) {
        Career updateCareer = careerRepository.save(careerMapper.dtoModel(careerDto));
        CareerDto updatedCareerDto = careerMapper.modelToDto(updateCareer);
        return updatedCareerDto;
    }

    @Override
    public CareerDto findCareerById(Long id) {
        Career career = careerRepository.findCareerById(id);
        if (career == null){
            throw new EntityNotFoundException("the career with id: "+id+" is not found");
        }
        CareerDto careerDto = careerMapper.modelToDto(career);
        return careerDto;
    }

    @Override
    public List<CareerDto> findAllCareers(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Career> careers = careerRepository.findAll(pageable);

        List<Career> careerList = careers.getContent();
        return careerList.stream().map(career -> careerMapper.modelToDto(career)).collect(Collectors.toList());
    }

    @Override
    public void deleteCareerById(Long id) {
        this.findCareerById(id);
        careerRepository.deleteById(id);
    }

}
