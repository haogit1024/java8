package com.czh.publish;

import java.util.ArrayList;
import java.util.List;

public class Trader {
    private final String name;
    private final String city;
    public Trader(String n, String c){
        this.name = n;
        this.city = c;
    }
    public String getName(){
        return this.name;
    }
    public String getCity(){
        return this.city;
    }
    public String toString(){
        return "Trader:"+this.name + " in " + this.city;
    }

    public List<Trader> getTraders(){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Trader> list = new ArrayList<>();
        list.add(raoul);
        list.add(mario);
        list.add(alan);
        list.add(brian);
        return list;
    }
}
