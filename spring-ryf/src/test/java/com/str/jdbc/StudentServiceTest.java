package com.str.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.*;

public class StudentServiceTest {
    String JDBC_URL = "jdbc:mysql://localhost:3306/test?useSSL=false&characterEncoding=utf8";
    String JDBC_USER = "root";
    String JDBC_PASSWORD = "Mysql57@deb";

    DataService dataService = new DataService(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    StudentService studentService = new StudentService(dataService);

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void listScores() {
        dataService.connDB();
        List<Student> students = studentService.listScores(dataService.getConn(), 2, 5);
        for (Student student : students) {
            student.printStudent();
        }
        dataService.closeDB();
    }

    @Test
    public void getScore() {
        dataService.connDB();
        Student student = studentService.getScore(dataService.getConn(), "小明");
        student.printStudent();
        dataService.closeDB();
    }
}