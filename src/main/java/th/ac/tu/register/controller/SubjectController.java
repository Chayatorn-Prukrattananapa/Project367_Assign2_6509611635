package th.ac.tu.register.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import th.ac.tu.register.model.Subject;
import th.ac.tu.register.services.SubjectService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService SubjectService) {
        this.subjectService = SubjectService;
    }

    // Sync available seats
    @PutMapping("/sync")
    public void syncAvailableSeats() {
        subjectService.syncAvailableSeats();
    }

    // Find all subjects
    @GetMapping
    public List<Subject> findAll() {
        return subjectService.findAll();
    }    

    // Find subject by subjectId
    @GetMapping("/{subjectId}")
    public Subject findBySubjectId(@PathVariable String subjectId) {
        return subjectService.findBySubjectId(subjectId);
    }

    // Add or withdraw subject
    @PutMapping("/add/{subjectId}")
    public ResponseEntity<Subject> addSubject(@PathVariable String subjectId) {
        Subject subject = subjectService.findBySubjectId(subjectId);
        return subjectService.addSubject(subject);
    }

    // Withdraw subject
    @PutMapping("/withdraw/{subjectId}")
    public ResponseEntity<Subject> withdrawSubject(@PathVariable String subjectId) {
        // Assuming you want to get the subjectId from the request body
        Subject subject = subjectService.findBySubjectId(subjectId);
        return subjectService.withdrawSubject(subject);
    }

    // Delete subject
    @DeleteMapping("/{subjectId}")
    public ResponseEntity<Void> deleteSubject(@PathVariable String subjectId) {
        subjectService.deleteBySubjectId(subjectId);
        return ResponseEntity.noContent().build();
    }
}