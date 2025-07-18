package com.salmakhd.android.androidwithjavaandxml.salma.salmarecyclerview;

public class CustomModel {
    String name;
    String grade;
    String age;

    public CustomModel(String name, String grade, String age) {
        this.name = name;
        this.grade = grade;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
