package Controller;

import domain.Student;
import domain.validators.StudentValidator;
import domain.validators.ValidatorException;
import repository.InMemoryRepository;
import repository.Repository;
import repository.StudentFileRepository;

public class Controller {
    private Repository<Long, Student> repository;
    private StudentValidator studentValidator;
    public Controller(Repository<Long,Student> studentRepository){
        this.repository=studentRepository;
        this.studentValidator=new StudentValidator();
    }

    public void addStudent(String[] studentstr)throws ValidatorException{
        Student student= new Student(studentstr[1],Long.valueOf(studentstr[2]));
        student.setId(Long.valueOf(studentstr[0]));
        try{
            repository.add(student);
        }
        catch (ValidatorException exc){
            throw exc;
        }

    }
    public String PrintStudents(){
        return repository.toString();
    }

    public void update(String[] studentStr) throws ValidatorException{

        Student student= new Student(studentStr[1],Long.valueOf(studentStr[2]));
        student.setId(Long.valueOf(studentStr[0]));
        try{
            repository.update(student);
        }
        catch (ValidatorException exc){
            throw exc;
        }

    }

    public String GetStudentByEntityId(Long id){
        //exceptii bla bla
        //To be changed a bit
        return repository.findOne(id).toString();
    }
    public void deleteStudent(Long id){
        //exceptii bla bla
        repository.delete(id);
    }

    public void saveRepository(){
        StudentFileRepository fileRepository=(StudentFileRepository) repository;
        fileRepository.saveToFile();
    }
}
