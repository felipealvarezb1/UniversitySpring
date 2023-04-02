package felipe.springframework.university.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "students")
@JsonIgnoreProperties("career")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "user_name", length = 50, nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @Column(name = "career_id", nullable = false)
    private Long careerId;

    @ManyToOne
    @JsonBackReference
    @JsonManagedReference
    @JoinColumn(name = "career_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Career career;

    @JsonBackReference
    @OneToMany(mappedBy = "student",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<GroupRegistration> registrations = new HashSet<>();
}
