package th.ac.tu.register.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import th.ac.tu.register.model.Student;
import th.ac.tu.register.model.Subject;
import th.ac.tu.register.services.SubjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;





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

    // Delete subject
    @DeleteMapping("/{subjectId}")
    public ResponseEntity<Void> deleteSubject(@PathVariable String subjectId) {
        subjectService.deleteBySubjectId(subjectId);
        return ResponseEntity.noContent().build();
    }

    //
    @GetMapping("/count/{subjectId}")
    public String getNumberOfStudents(@PathVariable String subjectId) {
        return subjectId + " Students: " + subjectService.getNumberOfStudents(subjectId);
    }
    public String getMethodName(@RequestParam String param) {
        return new String();
    }

    //Request Tae to list student by subjectId
    @GetMapping("/list/{subjectId}")
    public List<Student> getStudentList(@PathVariable String subjectId) {
        return subjectService.getStudentsBySubjectId(subjectId);
    } 
    
    //Provide Tae to return max seats
    @GetMapping("/seats/{subjectId}")
    public int getMaxSeats(@PathVariable String subjectId) {
        return subjectService.getMaxSeats(subjectId);
    }


    //Provide Tae to check seats enough or not
    @GetMapping("/seats/check/{subjectId}")
    public boolean isEnoughSeats(@PathVariable String subjectId){
        Subject subject = findBySubjectId(subjectId);
        return subjectService.isEnoughSeats(subject);
    }
    
    @GetMapping("/check/{subjectId}")
    public boolean isSubjectExist(@PathVariable String subjectId) {
        return subjectService.isSubjectExist(subjectId);
    }
    
}