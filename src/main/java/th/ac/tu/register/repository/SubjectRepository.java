package th.ac.tu.register.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import th.ac.tu.register.model.Subject;
public interface SubjectRepository extends JpaRepository<Subject, String> {
    Subject findBySubjectIdIgnoreCase(String subjectId);
}
