package repository;

/*
import domain.BaseEntity;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Sort<T extends BaseEntity> implements Comparator<T> {}
    //private List<Order> fields;
    //private List<String> args;

    public Sort(String...args){
        AtomicInteger asc= new AtomicInteger(1);
        this.args=new ArrayList<>();
        this.args= Arrays.asList(args);
        fields= new ArrayList<Order>();
        this.args.stream().forEach(arg->{
            switch (arg){
                case "asc": {
                    asc.set(1);
                    break;
                }
                case "desc":{
                    asc.set(-1);
                    break;
                }
                default:{
                    fields.add(new Order(asc.intValue(),arg));
                    break;
                }
            }
        });

    }
    @Override
    public int compare(T t1, T t2){
        for(Order o :fields ){
            Comparable p1= t1.getVariable(o.name);
            Comparable p2=t2.getVariable(o.name);
            int res=p1.compareTo(p2);
            res*=o.direction;
            if(res!=0)
                return res;
        }
        return 0;
    }


}

class Order{
    String name;
    int direction;

    public Order(int direction,String name){
        this.direction=direction;
        this.name=name;
    }
}

 */

