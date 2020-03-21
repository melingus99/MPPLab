package domain;

public class LabProblem extends BaseEntity<Long> {



    private String name;

    private String dueTime;

    public LabProblem(String name,String dueTime){
        this.name=name;
        this.dueTime=dueTime;
    }
    public String getDueTime() {
        return dueTime;
    }

    public void setDueTime(String dueTime) {
        this.dueTime = dueTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public Comparable getVariable(String variable){
        switch (variable){
            case "dueTime":{
                return (Comparable)this.getDueTime();
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
    @Override
    public String toString(){
        return "Id:"+this.getId()+", Name: "+name+", due time: "+dueTime;
    }
}
