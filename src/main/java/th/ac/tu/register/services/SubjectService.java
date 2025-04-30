package th.ac.tu.register.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import th.ac.tu.register.model.Subject;
import th.ac.tu.register.repository.SubjectRepository;

/**
 * Requests to Tae: 
 *                  
 * Respond to Tae:  EnrolledStatus, List of subjectId 
 */

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    // Method Part

    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    public Subject findBySubjectId(String subjectId) {
        Subject subject = subjectRepository.findBySubjectIdIgnoreCase(subjectId);
        if (subject == null) {
            throw new EntityNotFoundException("Subject not found with subjectId: " + subjectId);
        }
        return subject;
    }

    public ResponseEntity<Subject> addSubject(Subject subject) {
        if(subject.getAvailableStudents() > 0){
            subject.setAvailableStudents(subject.getAvailableStudents() - 1);
            /* communicate to Tae */
            subjectRepository.save(subject);
            return new ResponseEntity<>(subject, HttpStatus.CREATED);
        } else {
            System.err.println("Not enough available seats");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Subject> withdrawSubject(Subject subject) {
        if(subject.getAvailableStudents() < subject.getMaxSeats()){
            subject.setAvailableStudents(subject.getAvailableStudents() + 1);
            /* communicate to Tae */
            subjectRepository.save(subject);
            return new ResponseEntity<>(subject, HttpStatus.CREATED);
        } else {
            System.err.println("No one added to this subject");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
