package felipe.springframework.university.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
@Table(name = "groups")
@JsonIgnoreProperties("subject")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_name", length = 50, unique = true, nullable = false)
    private String name;

    @Column(name = "group_schedule", length = 50, nullable = false)
    private String schedule;

    @Column(name = "group_room", length = 50, nullable = false)
    private String room;

    @Column(name = "group_teacher", length = 50, nullable = false)
    private String teacher;

    @JsonIgnore
    @Column(name = "subject_id", nullable = false)
    private Long subjectId;

    @ManyToOne(optional = false)
    @JsonBackReference
    @JsonManagedReference
    @JoinColumn(name = "subject_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Subject subject;

    @JsonBackReference
    @OneToMany(mappedBy = "group",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<GroupRegistration> registrations = new HashSet<>();

}
