package Controller;

import domain.LabProblem;
import domain.Student;
import domain.validators.LabProblemValidator;
import domain.validators.StudentValidator;
import domain.validators.ValidatorException;
import repository.InMemoryRepository;
import repository.LabProblemFileRepository;
import repository.Repository;
import repository.StudentFileRepository;

public class LabProblemController {
    private Repository<Long, LabProblem> repository;
    private LabProblemValidator labProblemValidator;

    public LabProblemController(Repository<Long, LabProblem> labProblemRepository){
        this.repository=labProblemRepository;
        this.labProblemValidator=new LabProblemValidator();
    }

    public void add(String[] labProblemStr)throws ValidatorException{
        LabProblem labProblem= new LabProblem(labProblemStr[1],labProblemStr[2]);
        labProblem.setId(Long.valueOf(labProblemStr[0]));

        repository.add(labProblem);

    }
    public String PrintAll(){
        return repository.toString();
    }

    public void update(String[] labProblemStr) throws ValidatorException{
        LabProblem labProblem=new LabProblem(labProblemStr[1],labProblemStr[2]);
        labProblem.setId(Long.valueOf(labProblemStr[0]));
        repository.update(labProblem);
    }

    public String GetEntityById(Long id){
        return repository.findOne(id).toString();
    }
    public void delete(Long id){
        repository.delete(id);
    }

    public void saveRepository(){
        LabProblemFileRepository fileRepository=(LabProblemFileRepository) repository;
        fileRepository.saveToFile();
    }
}
