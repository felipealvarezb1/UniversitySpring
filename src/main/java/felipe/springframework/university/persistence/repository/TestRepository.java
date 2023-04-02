package felipe.springframework.university.persistence.repository;

import felipe.springframework.university.domain.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

    Test findTestById(Long id);
}
