package felipe.springframework.university.persistence.repository;

import felipe.springframework.university.domain.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findStudentById(Long id);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
