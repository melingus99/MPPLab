package domain;

public class Student extends BaseEntity<Long> {
    private String name;
    private Long group;



    public Student(String name, Long group){
        this.name=name;
        this.group=group;
    }
    @Override
    public String toString(){
        return this.name+" with Student group:"+ group.toString()+" ";
    }

    public String getName() {
        return this.name;
    }

    public void setName(String new_name){
        this.name=new_name;
    }

    public Long getGroup() {
        return group;
    }

    public void setGroup(Long group) {
        this.group = group;
    }
}
