package felipe.springframework.university.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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
@Table(name = "registration")
public class GroupRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable=false, updatable=false)
    private Student student;

    @JsonIgnore
    @Column(name = "group_id", nullable = false)
    private Long groupId;

    @ManyToOne
    @JoinColumn(name = "group_id", insertable=false, updatable=false)
    private Group group;

    private LocalDateTime registeredAt;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "registration",
            fetch = FetchType.LAZY)
    private List<Test> tests = new ArrayList<>();

}
