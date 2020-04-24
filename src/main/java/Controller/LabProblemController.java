package Controller;

import domain.LabProblem;
import domain.Student;
import domain.validators.LabProblemValidator;
import domain.validators.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.FileRepository.LabProblemFileRepository;
import repository.LabProblemsRepositoryInterface;
import repository.Repository;
//import repository.Sort;
import repository.SortingRepository;
import repository.StudentRepositoryInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LabProblemController implements LabProblemControllerInterface{

    public static final Logger log = LoggerFactory.getLogger(LabProblemController.class);
    @Autowired
    private LabProblemsRepositoryInterface repository;

    @Override
    @Transactional
    public void update(String[] labProblemStr) throws ValidatorException{
        LabProblem labProblem=new LabProblem(labProblemStr[1],labProblemStr[2]);
        labProblem.setId(Long.valueOf(labProblemStr[0]));
        log.trace("updateLabProblem - method entered: labProblem={}", labProblem);
        repository.findById(labProblem.getId())
                .ifPresent(l -> {
                    l.setName(labProblem.getName());
                    l.setDueTime(labProblem.getDueTime());
                    log.debug("updateLabProblem - updated: l={}", l);
                });
        log.trace("updateLabProblem - method finished");
    }

    @Override
    public Optional<LabProblem> GetByEntityId(Long id){
        return repository.findById(id);
    }

    @Override
    public void delete(Long id){
        repository.deleteById(id);
    }

    @Override
    public void add(String[] labProblemStr){
        LabProblem labProblem=new LabProblem(labProblemStr[1],labProblemStr[2]);
        labProblem.setId(Long.valueOf(labProblemStr[0]));
        repository.save(labProblem);
    }

    @Override
    public List<LabProblem> getAll() {
        return repository.findAll();
    }

}
