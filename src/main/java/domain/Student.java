package domain;

import javax.persistence.Entity;

@Entity
public class Student extends BaseEntity<Long> {

    private String name;
    private Long groupnr;


    public Student(){

    }

    public Student(String name, Long groupnr){
        this.name=name;
        this.groupnr=groupnr;
    }
    @Override
    public String toString(){
        return "Id:"+this.getId()+", name: "+this.name+", group:"+ groupnr.toString()+" ";
    }

    public String getName() {
        return this.name;
    }

    public void setName(String new_name){
        this.name=new_name;
    }

    public Long getGroup() {
        return groupnr;
    }

    public void setGroup(Long group) {
        this.groupnr = group;
    }

    /*
    @Override
    public Comparable getVariable(String variable){
        switch (variable){
            case "group":{
                return (Comparable)this.getGroup();
            }
            case "name":{
                return (Comparable)this.getName();
            }
            case "id":{
                return (Comparable)this.getId();
            }
        }
        return null;
    }
    */
}
