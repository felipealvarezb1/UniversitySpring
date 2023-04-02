package felipe.springframework.university.persistence.repository;

import felipe.springframework.university.domain.model.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerRepository extends JpaRepository<Career, Long> {

    Career findCareerById(Long id);

    Boolean existsByName(String name);

}
