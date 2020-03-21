package Controller;

import domain.LabProblem;
import domain.Student;
import domain.validators.LabProblemValidator;
import domain.validators.ValidatorException;
import repository.FileRepository.LabProblemFileRepository;
import repository.Repository;
import repository.Sort;
import repository.SortingRepository;

import java.util.ArrayList;

public class LabProblemController {
    private SortingRepository<Long, LabProblem> repository;
    private LabProblemValidator labProblemValidator;

    public LabProblemController(SortingRepository<Long, LabProblem> labProblemRepository){
        this.repository=labProblemRepository;
        this.labProblemValidator=new LabProblemValidator();
    }

    public void add(String[] labProblemStr)throws ValidatorException{
        LabProblem labProblem= new LabProblem(labProblemStr[1],labProblemStr[2]);
        labProblem.setId(Long.valueOf(labProblemStr[0]));

        repository.add(labProblem);

    }
    public String PrintAll(){
        Sort<LabProblem> sort=new Sort("asc","name","desc","dueTime");

        ArrayList<LabProblem> labProblems=(ArrayList<LabProblem>)this.repository.findAll(sort);
        String str=labProblems.stream().map(entity->entity.toString()).reduce("",(s1,s2)->s1+="\n"+s2);
        return str;
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
        ((LabProblemFileRepository) repository).saveToFile();
    }
}
