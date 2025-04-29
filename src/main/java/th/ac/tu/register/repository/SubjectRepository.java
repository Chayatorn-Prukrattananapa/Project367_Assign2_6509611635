package th.ac.tu.register.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.ac.tu.register.model.Subject;
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Optional<Subject> findBySubjectId(String subjectId, String section);
}
