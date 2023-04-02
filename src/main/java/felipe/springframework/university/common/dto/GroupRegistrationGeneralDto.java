package felipe.springframework.university.common.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class GroupRegistrationGeneralDto {

    private Long studentId;

    private Long groupId;

    private LocalDateTime registeredAt;


}
