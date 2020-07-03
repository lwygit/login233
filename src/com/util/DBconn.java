package com.util;

import java.sql.*;

public class DBconn {
    /**
     * 使用了c3p0连接池 此文件已无用
     *
     */
    //static String url = "jdbc:mysql://localhost:3306/test?useunicuee=true& characterEncoding=utf8";
    static String username = "root";
    static String password = "Liangwy189";
    static String table = "test";
    static String url = "jdbc:mysql://localhost:3306/" + table + "?characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useSSL=false";
    static Connection conn = null;
    static ResultSet rs = null;
    static PreparedStatement ps = null;

    public static void init() {
        System.out.println("DB init");
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("connected");
        } catch (Exception e) {
            System.out.println("init [SQL驱动程序初始化失败！]");
            e.printStackTrace();
        }
    }

    public static int addUpdDel(String sql) {
        int i = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            i = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("sql数据库增删改异常");
            e.printStackTrace();
        }

        return i;
    }

    public static ResultSet selectSql(String sql) {
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("sql数据库查询异常");
            e.printStackTrace();
        }
        return rs;
    }

    public static void closeConn() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("sql数据库关闭异常");
            e.printStackTrace();
        }
    }
}