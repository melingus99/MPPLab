package repository;

import domain.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;

public class StudentRepository{
    private ArrayList<Student> studentRepo;

    public StudentRepository(){
        studentRepo= new ArrayList<Student>();
    }

    public List<Student> getStudentRepo(){
        return studentRepo;
    }

    public void addStudent(String name){
        Student student=new Student(name);
        studentRepo.add(student);
    }

    @Override
    public String toString(){
        String str=studentRepo.stream().map(student->student.toString()).reduce("",(s1,s2)->s1+="\n"+s2);
        return str;
    }
}
