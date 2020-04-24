package Controller;

import domain.Student;
import domain.validators.StudentValidator;
import domain.validators.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.*;
import repository.FileRepository.StudentFileRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentController implements StudentControllerInterface {


    public static final Logger log = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    private StudentRepositoryInterface repository;


    @Override
    public void add(String[] studentstr)throws ValidatorException{
        Student student= new Student(studentstr[1],Long.valueOf(studentstr[2]));
        student.setId(Long.valueOf(studentstr[0]));
            if(GetByEntityId(student.getId()).isEmpty())
                repository.save(student);


    }

    @Override
    @Transactional
    public void update(String[] studentstr) {
        Student student= new Student(studentstr[1],Long.valueOf(studentstr[2]));
        student.setId(Long.valueOf(studentstr[0]));
        log.trace("updateStudent - method entered: student={}", student);
        repository.findById(student.getId())
                .ifPresent(s -> {
                    s.setName(student.getName());
                    s.setGroup(student.getGroup());
                    log.debug("updateStudent - updated: s={}", s);
                });
        log.trace("updateStudent - method finished");
    }

    @Override
    public Optional<Student> GetByEntityId(Long id){
        return repository.findById(id);
    }

    @Override
    public void delete(Long id){

            repository.deleteById(id);
    }


    @Override
    public List<Student> getAll() {
        return repository.findAll();
    }
}
