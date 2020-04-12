package com.str.jdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StudentService {

    private DataService dataService;

    public StudentService(DataService dataService) {
        this.dataService = dataService;
    }

    public List<Student> listScores(Connection conn, int grade, int count) {
        List<Student> list = new ArrayList<>();

        String sql = "SELECT id, name, gender, grade, score" +
                " FROM students" +
                " WHERE grade=?" +
                " LIMIT 0,?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, grade);
            ps.setObject(2, count);
            ResultSet rs = ps.executeQuery();

            System.out.println(grade + "年级成绩单");
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                int gender = rs.getInt("gender");
                int score = rs.getInt("score");
                Student student = new Student(id, name, gender, grade, score);
                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public Student getScore(Connection conn, String name) {
        Student result = null;

        String sql = "SELECT id, gender, grade, score" +
                " FROM students" +
                " WHERE name=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, name);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                long id = rs.getLong("id");
                int gender = rs.getInt("gender");
                int grade = rs.getInt("grade");
                int score = rs.getInt("score");
                result = new Student(id, name, gender, grade, score);
            }
        } catch (SQLException e) {
        }

        return result;
    }

}
