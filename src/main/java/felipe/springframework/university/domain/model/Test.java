package felipe.springframework.university.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "tests")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "test_name", length = 30, nullable = false)
    private String name;

    @Column(name = "test_percentage", nullable = false)
    private int percentage;

    @Column(name = "test_score", nullable = false)
    private double score;

    @JsonIgnore
    @Column(name = "registration_id", nullable = false)
    private Long registrationId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "registration_id", referencedColumnName = "id", insertable = false, updatable = false)
    private GroupRegistration registration;
}
