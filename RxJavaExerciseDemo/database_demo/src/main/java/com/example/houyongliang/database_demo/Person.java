package com.example.houyongliang.database_demo;

/**
 * Created by houyongliang on 2018/3/22 16:57.
 * Function(功能):
 */

public class Person {
    public Person(int id, String name, int age) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Person() {
        super();
        // TODO Auto-generated constructor stub
    }

    private int id;
    private String name;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", age=" + age + "]";
    }


}
