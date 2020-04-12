package com.str.jdbc;

public class Student {
    private long id;
    private String name;
    private int gender;
    private int grade;
    private int score;

    public Student(long id, String name, int gender, int grade, int score) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.grade = grade;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getGender() {
        return gender;
    }

    public int getGrade() {
        return grade;
    }

    public int getScore() {
        return score;
    }

    public void printStudent() {
        System.out.println(this.id + "\t" + this.name + "\t" + this.gender +"\t" + this.grade + "\t" + this.score);
    }

}
