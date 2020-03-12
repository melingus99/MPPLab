package Controller;

import domain.Student;
import domain.validators.StudentValidator;
import domain.validators.ValidatorException;
import repository.InMemoryRepository;
import repository.Repository;
import repository.StudentFileRepository;

public class StudentController {
    private Repository<Long, Student> repository;
    private StudentValidator studentValidator;
    public StudentController(Repository<Long,Student> studentRepository){
        this.repository=studentRepository;
        this.studentValidator=new StudentValidator();
    }

    public void addStudent(String[] studentstr)throws ValidatorException{
        Student student= new Student(studentstr[1],Long.valueOf(studentstr[2]));
        student.setId(Long.valueOf(studentstr[0]));

            repository.add(student);


    }
    public String PrintStudents(){
        return repository.toString();
    }

    public void update(String[] studentStr) throws ValidatorException{

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
        StudentFileRepository fileRepository=(StudentFileRepository) repository;
        fileRepository.saveToFile();
    }
}
