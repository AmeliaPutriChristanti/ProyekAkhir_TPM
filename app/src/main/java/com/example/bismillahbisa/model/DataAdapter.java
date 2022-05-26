package com.example.bismillahbisa.model;


public class DataAdapter {

    public DataAdapter() {
    }

    public DataAdapter(String name, String clock) {
        this.name = name;
        this.clock = clock;
    }

    private String name, clock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }
}
