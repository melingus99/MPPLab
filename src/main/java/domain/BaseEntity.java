package domain;


public class BaseEntity<ID> {
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public Comparable getVariable(String variable){
        return (Comparable) id;
    }

    @Override
    public String toString() {
        return "Entity with id: "+id;
    }
}