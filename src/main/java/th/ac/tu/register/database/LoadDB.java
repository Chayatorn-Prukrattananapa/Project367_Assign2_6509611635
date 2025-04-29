package th.ac.tu.register.database;

import th.ac.tu.register.model.Subject;
import org.springframework.boot.CommandLineRunner;

import th.ac.tu.register.repository.SubjectService;

public class LoadDB implements CommandLineRunner {
    private final SubjectService subjectRepository;
        
    public LoadDB(SubjectService subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public void run(String... args) throws Exception {
        subjectRepository.save(new Subject());
    }
}
