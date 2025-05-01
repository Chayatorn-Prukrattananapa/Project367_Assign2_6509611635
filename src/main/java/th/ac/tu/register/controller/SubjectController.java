package th.ac.tu.register.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import th.ac.tu.register.model.Subject;
import th.ac.tu.register.services.SubjectService;


@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService SubjectService) {
        this.subjectService = SubjectService;
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
    @DeleteMapping("/delete/{subjectId}")
    public ResponseEntity<Void> deleteSubject(@PathVariable String subjectId) {
        // Request Response Status for deleting student by subjectId
        
        return subjectService.deleteBySubjectId(subjectId);
    }
}
