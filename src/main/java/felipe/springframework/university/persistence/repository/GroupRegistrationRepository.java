package felipe.springframework.university.persistence.repository;

import felipe.springframework.university.domain.model.GroupRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRegistrationRepository extends JpaRepository<GroupRegistration, Long> {

    GroupRegistration findRegistrationById(Long id);

    GroupRegistration findByStudentIdAndGroupId(Long studentId, Long groupId);
}
