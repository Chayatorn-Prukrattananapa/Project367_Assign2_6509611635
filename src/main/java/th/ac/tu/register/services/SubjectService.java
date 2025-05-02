package th.ac.tu.register.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        if(subject.getAvailableSeats() > 0){
            subject.setAvailableSeats(subject.getAvailableSeats() - 1);
            subjectRepository.save(subject);
            return new ResponseEntity<>(subject, HttpStatus.CREATED);
        } else {
            System.err.println("Not enough available seats");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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

    public ResponseEntity<Subject> withdrawSubject(Subject subject) {
        if(subject.getAvailableSeats() < subject.getMaxSeats()){
            subject.setAvailableSeats(subject.getAvailableSeats() + 1);
            subjectRepository.save(subject);
            return new ResponseEntity<>(subject, HttpStatus.CREATED);
        } else {
            System.err.println("No one added to this subject");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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

    public ResponseEntity<Void> deleteBySubjectId(String subjectId) {
        String externalApiUrl = "http://localhost:8080/api/enroll/" + subjectId;

        try {
            // Call the external API to delete the enrollment data
            ResponseEntity<Void> response = restTemplate.exchange(
            externalApiUrl,
            HttpMethod.DELETE,
            null,
            Void.class
            );
            if (response.getStatusCode() == HttpStatus.OK) {
                // If the external API call is successful, delete the subject from the local database
                Subject subject = subjectRepository.findBySubjectIdIgnoreCase(subjectId);
                if (subject != null) {
                    subjectRepository.delete(subject);
                    System.out.println("Subject deleted successfully: " + subjectId);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else {
                    System.err.println("Subject not found in local database: " + subjectId);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } else {
                System.err.println("Failed to delete enrollment data for subjectId: " + subjectId);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            System.err.println("Error occurred while deleting subject: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public int getAvailableSeats(String subjectId) {
        return subjectRepository.findBySubjectIdIgnoreCase(subjectId).getAvailableSeats();
        
    }

}
