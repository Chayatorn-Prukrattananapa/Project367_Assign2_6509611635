package th.ac.tu.register.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import th.ac.tu.register.model.Subject;
import th.ac.tu.register.services.SubjectService;


@RestController
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService SubjectService) {
        this.subjectService = SubjectService;
    }

    @PostMapping("/api/subject")
    public List<Subject> findAll() {
        return subjectService.findAll();
    }    

    @GetMapping("/subjects/{subjectId}/{section}")
    public Subject findBySubjectId(@PathVariable String subjectId, @PathVariable String section) {
        return subjectService.findBySubjectId(subjectId, section);
    }

    @PutMapping("/api/subject/add-subject/{subjectId}/{section}")
    public ResponseEntity<Subject> addSubject(@PathVariable String subjectId, @PathVariable String section) {
        Subject subject = subjectService.findBySubjectId(subjectId, section);
        return subjectService.addSubject(subject);
    }
    @PutMapping("/api/subject/withdraw-subject/{subjectId}/{section}")
    public ResponseEntity<Subject> withdrawSubject(@PathVariable String subjectId, @PathVariable String section) {
        Subject subject = subjectService.findBySubjectId(subjectId, section);
        return subjectService.withdrawSubject(subject);
    }
}
