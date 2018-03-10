package com.example.bindu.inclass3fall2014;

import java.io.Serializable;

/**
 * Created by bindu on 1/28/2018.
 */

public class Employee implements Serializable {
    String name;
    String age;
    String email;
    String phone;
    String department;

    public Employee(String name, String age, String email, String phone, String department) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
