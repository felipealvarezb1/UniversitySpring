package felipe.springframework.university.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "careers")
@JsonIgnoreProperties("students")
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "career_name", unique = true, length = 50, nullable = false)
    private String name;

    @Column(name = "career_description", length = 500, nullable = false)
    private String description;

    @Column(name = "career_credits", nullable = false)
    private int totalCredits;

    @JsonBackReference
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "career",
            fetch = FetchType.LAZY)
    private Set<Student> students = new HashSet<>();

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "career",
            fetch = FetchType.LAZY)
    private Set<Subject> subjects = new HashSet<>();

}
