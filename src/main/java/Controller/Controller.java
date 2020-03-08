package Controller;

import repository.StudentRepository;

public class Controller {
    private StudentRepository studentRepository;

    public Controller(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }
    public void addStudent(String name){
        //exceptii bla bla
        studentRepository.addStudent(name);
    }
    public String PrintStudents(){
        return studentRepository.toString();
    }
}
