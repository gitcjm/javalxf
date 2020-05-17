package com.str.jdbc;

import org.springframework.stereotype.Component;

import java.sql.*;

public class DataService {
    private String jdbc_url;
    private String jdbc_user;
    private String jdbc_password;

    private Connection conn;

    public DataService(String JDBC_URL, String JDBC_USER, String JDBC_PASSWORD) {
        this.jdbc_url = JDBC_URL;
        this.jdbc_user = JDBC_USER;
        this.jdbc_password = JDBC_PASSWORD;
    }

    public Connection getConn() {
        return this.conn;
    }

    public void connDB() {
        try {
           this.conn = DriverManager.getConnection(
                    this.jdbc_url, this.jdbc_user, this.jdbc_password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Database connected.");
    }

    public void closeDB() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Database disconnected.");
    }

    // 绝对禁止的不安全SQL查询
    /*public static void listStudents(int count) {
        String sql = "SELECT id, name, gender, grade, score" +
                " FROM students" +
                " LIMIT 0," + count;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                long id = rs.getLong(1);
                String name = rs.getString(2);
                byte gender = rs.getByte(3);
                int grade = rs.getInt(4);
                int score = rs.getInt(5);
                System.out.println(id + "\t" + name + "\t" + gender +
                        "\t" + grade + "\t" + score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    // 防止SQL注入的安全查询
    /*public static void listScores(int grade, int count) {
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
                String gender = rs.getString("gender");
                int score = rs.getInt("score");
                System.out.println(id + "\t" + name + "\t" + gender +
                        "\t" + score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

}
