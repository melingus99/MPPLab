package domain;

public class Student extends BaseEntity<Long> {
    private String name;
    private String studentId;

    public Student(String id,String name){
        this.name=name;
        studentId=id;
    }
    @Override
    public String toString(){
        return this.name+" with Student id:"+ studentId+" ";
    }

    public String getName() {
        return this.name;
    }

    public void setName(String new_name){
        this.name=new_name;
    }

    public String getStudentId(){
        return studentId;
    }

    public void setStudentId(String newId)
    {
        studentId=newId;
    }

}
