package felipe.springframework.university.persistence.repository;

import felipe.springframework.university.domain.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Group findGroupById(Long id);

    Boolean existsByName(String name);
}
