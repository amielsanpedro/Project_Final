package com.SanpedroAlatraca.project.work_it_out;

public class Measurements {
    String height;
    String weight;

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {

        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Measurements() {

    }

    public Measurements(String height, String weight) {

        this.height = height;
        this.weight = weight;
    }
}
