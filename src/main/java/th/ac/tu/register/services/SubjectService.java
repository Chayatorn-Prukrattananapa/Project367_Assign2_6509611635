package th.ac.tu.register.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import th.ac.tu.register.model.Student;
import th.ac.tu.register.model.Subject;
import th.ac.tu.register.repository.SubjectRepository;

/**
 * Request:     Search for Student that has registered for a subject 
 *              Delete All Students that has registered for a subject
 * Response:    List of Subjects
 *              Response Status for enrollment (Add/Withdraw)
 */

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final RestTemplate restTemplate;
    
    public SubjectService(SubjectRepository subjectRepository, RestTemplate restTemplate) {
        this.subjectRepository = subjectRepository;
        this.restTemplate = restTemplate;
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

    @SuppressWarnings("null")
    public long getNumberOfStudents(String subjectId) {
        String externalApiUrl = "http://localhost:8080/api/enroll/count/" + subjectId;
        try {
            ResponseEntity<Integer> response = restTemplate.getForEntity(externalApiUrl, Integer.class);
            return response.getBody();
        } catch (Exception e) {
            System.err.println("Error occurred while fetching number of students: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    public List<Student> getStudentsBySubjectId(String subjectId) {
        String externalApiUrl = "http://localhost:8080/api/enroll/" + subjectId;
    
        try {
            Student[] students = restTemplate.getForObject(externalApiUrl, Student[].class);
            
            if (students == null || students.length == 0) {
                System.err.println("No students found for subjectId: " + subjectId);
                return List.of();
            }
            return List.of(students);
        } catch (Exception e) {
            System.err.println("Error occurred while fetching students: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }


    public int getMaxSeats(String subjectId) {
        return subjectRepository.findBySubjectIdIgnoreCase(subjectId).getMaxSeats();
    }

    @SuppressWarnings("null")
    public boolean isEnoughSeats(Subject subject){
        String url = "http://localhost:8080/api/enroll/count/" + subject.getSubjectId();
        try {
            int studentCount = restTemplate.getForObject(url, Integer.class);
            return subject.getMaxSeats() > studentCount ;
        } catch (Exception e) {
            System.err.println("Error occurred while fetching students");
            e.printStackTrace();
        }
        return false;
    }
    
    public ResponseEntity<Void> deleteBySubjectId(String subjectId){
        String url = "http://localhost:8080/api/enroll/" + subjectId;
        try {
            restTemplate.delete(url);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            System.err.println("Error occurred while deleting enrollment: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
