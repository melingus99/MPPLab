package Controller;

import domain.Student;
import domain.validators.ValidatorException;
import repository.InMemoryRepository;
import repository.Repository;
import repository.StudentFileRepository;

public class Controller {
    private Repository<Long, Student> repository;

    public Controller(Repository<Long,Student> studentRepository){
        this.repository=studentRepository;
    }

    public void addStudent(String[] studentstr){
        Student student= new Student(studentstr[1],studentstr[2]);
        student.setId(Long.valueOf(studentstr[0]));
        //exceptii bla bla
        repository.add(student);
    }
    public String PrintStudents(){
        return repository.toString();
    }

    public void update(String[] stringStr){

        Student student= new Student(stringStr[1],stringStr[2]);
        student.setId(Long.valueOf(stringStr[0]));
        //exceptii bla bla
        repository.update(student);
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
