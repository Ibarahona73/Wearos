package com.example.myapplication;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {


    private String name;
    private Date completionDate;
    private boolean isCompleted;

    public Task(String name, Date completionDate) {
        this.name = name;
        this.completionDate = completionDate;
        this.isCompleted = false;
    }

    public String getName() {
        return name;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return
                "Tarea:" +'\n'+  name + '\n' +
                "Creada:" + completionDate + '\n'+
                "Completado:" + isCompleted;

    }
}

