package com.example.bindu.inclass03;

import java.io.Serializable;

/**
 * Created by bindu on 1/29/2018.
 */

public class Student implements Serializable {

    String name;
    String email;
    String dept;
    String mood;

    public Student(String name, String email, String dept, String mood) {
        this.name = name;
        this.email = email;
        this.dept = dept;
        this.mood = mood;
    }

    @Override
    public String toString() {
        return "com.example.bindu.inclass03.Student{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dept='" + dept + '\'' +
                ", mood='" + mood + '\'' +
                '}';
    }
}


