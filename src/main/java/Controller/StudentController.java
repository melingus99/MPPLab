package Controller;

import domain.Student;
import domain.validators.StudentValidator;
import domain.validators.ValidatorException;
import repository.*;
import repository.FileRepository.StudentFileRepository;

import java.util.ArrayList;

public class StudentController {
    private SortingRepository<Long, Student> repository;
    private StudentValidator studentValidator;

    public StudentController(SortingRepository<Long, Student> studentRepository){
        this.repository=studentRepository;
        this.studentValidator=new StudentValidator();
    }

    public void addStudent(String[] studentstr)throws ValidatorException{
        Student student= new Student(studentstr[1],Long.valueOf(studentstr[2]));
        student.setId(Long.valueOf(studentstr[0]));

            repository.add(student);


    }
    public String PrintStudents(){
        Sort<Student> sort=new Sort("asc","name","desc","group");

        ArrayList<Student> students=(ArrayList<Student>)this.repository.findAll(sort);
        String str=students.stream().map(entity->entity.toString()).reduce("",(s1,s2)->s1+="\n"+s2);
        return str;
    }

    public void update(String[] studentStr) throws ValidatorException {

        Student student= new Student(studentStr[1],Long.valueOf(studentStr[2]));
        student.setId(Long.valueOf(studentStr[0]));
            repository.update(student);

    }

    public String GetStudentByEntityId(Long id){
        return repository.findOne(id).toString();
    }

    public void deleteStudent(Long id){

            repository.delete(id);
    }

    public void saveRepository(){
            ((StudentFileRepository) repository).saveToFile();
    }
}
