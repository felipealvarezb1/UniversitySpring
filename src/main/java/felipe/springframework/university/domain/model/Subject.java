package felipe.springframework.university.domain.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "subjects")
@JsonIgnoreProperties("career")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject_name", length = 50, nullable = false)
    private String name;

    @Column(name = "subject_credits", nullable = false)
    private int  credits;

    @Column(name = "subject_start_date", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startDate;

    @Column(name = "subject_end_date", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endDate;

    @JsonIgnore
    @Column(name = "career_id", nullable = false)
    private Long careerId;

    @ManyToOne(optional = false)
    @JsonBackReference
    @JsonManagedReference
    @JoinColumn(name = "career_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Career career;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "subject",
            fetch = FetchType.LAZY)
    private Set<Group> groups = new HashSet<>();
}
