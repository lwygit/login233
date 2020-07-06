package com.dao;

import com.entity.User;
import com.util.C3P0Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao1 {
    private static UserDao1 instance = new UserDao1();

    private UserDao1() {
    }

    public static UserDao1 getInstance() {
        return instance;
    }

    //注册
    public static boolean register(User user) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        conn = C3P0Util.getConnection();
        try {
            ps = conn.prepareStatement("insert into user(name,password,sex,home,info) values(?,?,?,?,?)");
            ps.setString(1, user.getName());
            ps.setString(2, user.getPwd());
            ps.setString(3, user.getSex());
            ps.setString(4, user.getHome());
            ps.setString(5, user.getInfo());
            ps.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            flag = false;
        } finally {
            C3P0Util.release(conn, ps, null);
        }
        return flag;
    }

    public boolean login(String name, String password) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        conn = C3P0Util.getConnection();
        try {
            ps = conn.prepareStatement("Select * from user where name=? and password=?");
            ps.setString(1, name);
            ps.setString(2, password);
            rs = ps.executeQuery();
//            ps = conn.prepareStatement("Select * from user where name='" + name + "' and password='" + password + "'");
//            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("name").equals(name) && rs.getString("password").equals(password)) {
                    flag = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            C3P0Util.release(conn, ps, rs);
        }
        return flag;
    }

    public static List<User> getUserAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        conn = C3P0Util.getConnection();
        try {
            ps = conn.prepareStatement("Select * from user");
            rs = ps.executeQuery();
            List<User> list = new ArrayList<User>();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPwd(rs.getString("password"));
                user.setSex(rs.getString("sex"));
                user.setHome(rs.getString("home"));
                user.setInfo(rs.getString("info"));
                list.add(user);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            C3P0Util.release(conn, ps, rs);
        }
        return null;
    }

    public static boolean update(User user) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        conn = C3P0Util.getConnection();
        try {
            ps = conn.prepareStatement("update user set name =?, password =?, sex =?, home =?, info =? where id = ?");
            ps.setString(1, user.getName());
            ps.setString(2, user.getPwd());
            ps.setString(3, user.getSex());
            ps.setString(4, user.getHome());
            ps.setString(5, user.getInfo());
            ps.setInt(6, user.getId());
            ps.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            C3P0Util.release(conn, ps, null);
        }
        return flag;
    }

    public static boolean delete(int id) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        conn = C3P0Util.getConnection();
        try {
            ps = conn.prepareStatement("delete from user where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            C3P0Util.release(conn, ps, null);
        }
        return flag;
    }
}
