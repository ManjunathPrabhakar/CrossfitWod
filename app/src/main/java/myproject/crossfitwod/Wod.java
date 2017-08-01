package myproject.crossfitwod;

/**
 * Created by Saewon on 2017-08-01.
 */

public class Wod {

    public String name, wod, rep;

    public Wod( String name, String exercise, String rep){
        this.name = name;
        this.wod = exercise;
        this.rep = rep;
    }
    public String getName(){
        return name;
    }
    public String getExercise(){
        return wod;
    }
    public String getRep(){
        return rep;
    }
    public void addExtraInfo(String addExer, String addRep){
        wod = wod + " \n"+ addExer;
        rep = rep + " \n" + addRep;

    }
}
