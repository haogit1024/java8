package com.czh.publish;

public class Apple {
    private int weight;
    private String color;
    public Apple(){
        this.color = "default";
    }

    public Apple(int weight) {
        this.weight = weight;
    }

    public Apple(int weight, String color) {
        this.weight =weight;
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String test(){
        return "this is apple";
    }
}
