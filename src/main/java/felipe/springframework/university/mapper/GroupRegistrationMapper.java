package felipe.springframework.university.mapper;

import felipe.springframework.university.common.dto.GroupRegistrationDto;
import felipe.springframework.university.common.dto.GroupRegistrationGeneralDto;
import felipe.springframework.university.domain.model.GroupRegistration;
import felipe.springframework.university.domain.model.Test;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class GroupRegistrationMapper {

    @Mapping(target = "finalScore", expression = "java(getFinalScore(groupRegistration.getTests()))")
    @Mapping(target = "approved", expression = "java(isApproved(groupRegistrationDto.getFinalScore()))")
    public abstract GroupRegistrationDto modelToDto(GroupRegistration groupRegistration);

    public abstract GroupRegistration dtoToModel(GroupRegistrationDto groupRegistrationDto);

    public abstract GroupRegistrationGeneralDto modelToGeneralDto(GroupRegistration groupRegistration);

    public abstract GroupRegistration generalDtoToModel(GroupRegistrationGeneralDto groupRegistrationGeneralDto);

    protected Double getFinalScore(List<Test> tests){

        double finalScore = 0.0;
        double acumPercentage = 0.0;

        for(Test test:tests){
            acumPercentage = acumPercentage + test.getPercentage();

            double operation = test.getScore() * (test.getPercentage()/100.0);
            finalScore = finalScore + operation;

        }
        return finalScore;
    }

    protected Boolean isApproved(double finalScore){
        if ( finalScore > 2.95){
            return true;
        } else {
            return false;
        }
    }

}
