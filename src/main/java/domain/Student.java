package domain;

public class Student {
    private String name;
    private static int id=0;
    private int studentId;

    public Student(String name){
        this.name=name;
        studentId=id;
        id++;
    }
    @Override
    public String toString(){
        return this.name+" with id:"+Integer.toString(studentId);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String new_name){
        this.name=new_name;
    }

    public int getId(){
        return studentId;
    }

    public void setId(int newId){
        studentId=newId;
    }
}
