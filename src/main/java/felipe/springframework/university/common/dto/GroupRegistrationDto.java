package felipe.springframework.university.common.dto;

import felipe.springframework.university.domain.model.Test;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class GroupRegistrationDto extends GroupRegistrationGeneralDto{

    private List<Test> tests;

    private double finalScore;

    private Boolean approved;
}
