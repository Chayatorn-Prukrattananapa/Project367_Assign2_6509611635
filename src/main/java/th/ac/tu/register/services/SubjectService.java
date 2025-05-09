package th.ac.tu.register.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import jakarta.persistence.EntityNotFoundException;
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

    // GET http://localhost:2025/api/subject
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    // GET http://localhost:2025/api/subject/{subjectId}
    public Subject findBySubjectId(String subjectId) {
        Subject subject = subjectRepository.findBySubjectIdIgnoreCase(subjectId);
        if (subject == null) {
            throw new EntityNotFoundException("Subject not found with subjectId: " + subjectId);
        }
        return subject;
    }

    // GET http://localhost:2025/api/subject/count/{subjectId}
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

    // GET http://localhost:2025/api/subject/list/{subjectId}
    public List<Student> getStudentsBySubjectId(String subjectId) {
        String externalApiUrl = "http://localhost:8080/api/enroll/" + subjectId;
    
        try {
            Student[] students = restTemplate.getForObject(externalApiUrl, Student[].class);
            
            if (students == null || students.length == 0) {
                System.err.println("No students found for subjectId: " + subjectId);
                return List.of();
            }
            return List.of(students);
        } catch (HttpClientErrorException.NotFound e) {
            System.err.println("Subject ID not found: " + subjectId + " (404)");
            return List.of();
        
        } catch (RestClientException e) {
            System.err.println("Unexpected error fetching subjectId " + subjectId + ": " + e.getMessage());
            return List.of();
        }
    }

    // GET http://localhost:2025/api/subject/seats/{subjectId}
    public int getMaxSeats(String subjectId) {
        return subjectRepository.findBySubjectIdIgnoreCase(subjectId).getMaxSeats();
    }

    // GET http://localhost:2025/api/subject/seats/check/{subjectId}
    @SuppressWarnings("null")
    public boolean isEnoughSeats(Subject subject){
        String url = "http://localhost:8080/api/enroll/count/" + subject.getSubjectId();
        try {
            int studentCount = restTemplate.getForObject(url, Integer.class);
            System.out.println(subject.getMaxSeats());
            System.out.println(studentCount);
            return subject.getMaxSeats() > studentCount ;
        } catch (Exception e) {
            System.err.println("Error occurred while fetching students");
            e.printStackTrace();
        }
        return false;
    }
    
    // DELETE http://localhost:2025/api/subject/{subjectId}
    public ResponseEntity<Void> deleteBySubjectId(String subjectId){
        if(!isSubjectExist(subjectId)) {
            System.err.println("This Course never existed");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            String url = "http://localhost:8080/api/enroll/" + subjectId;
            try {
                restTemplate.delete(url);
                subjectRepository.delete(findBySubjectId(subjectId));
                return ResponseEntity.noContent().build();
            } catch (Exception e) {
                System.err.println("Error occurred while deleting enrollment: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }
    }

    // GET  http://localhost:2025/api/subject/check/{subjectId}
    public boolean isSubjectExist(String subjectId) {
        return findBySubjectId(subjectId) != null;
    }
}
