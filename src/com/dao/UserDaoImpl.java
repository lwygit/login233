/**
 * 项目使用了单例模式，此文件无用 2020.7.6
 */

package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.User;
import com.util.DBconn;
import com.util.C3P0Util;

public class UserDaoImpl implements UserDao {

    public boolean register(User user) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        conn = C3P0Util.getConnection();
        try {
            ps = conn.prepareStatement("insert into user(name,password,sex,home,info) values(?,?,?,?,?)");
            ps.setString(1,user.getName());
            ps.setString(2,user.getPwd());
            ps.setString(3,user.getSex());
            ps.setString(4,user.getHome());
            ps.setString(5,user.getInfo());
            ps.executeUpdate();
//            ps = conn.prepareStatement("insert into user(name,password,sex,home,info) " +
//                    "values('" + user.getName() + "','" + user.getPwd() + "','" + user.getSex() + "','" + user.getHome() + "','" + user.getInfo() + "')");
//            ps.executeUpdate();
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

//    public boolean register(User user) {
//        boolean flag = false;
//        DBconn.init();
//        int i = DBconn.addUpdDel("insert into user(name,password,sex,home,info) " +
//                "values('" + user.getName() + "','" + user.getPwd() + "','" + user.getSex() + "','" + user.getHome() + "','" + user.getInfo() + "')");
//        if (i > 0) {
//            flag = true;
//        }
//        DBconn.closeConn();
//        return flag;
//    }

    public boolean login(String name, String password) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        conn = C3P0Util.getConnection();
        try {
            ps = conn.prepareStatement("Select * from user where name=? and password=?");
            ps.setString(1,name);
            ps.setString(2,password);
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

//    public boolean login(String name, String password) {
//        boolean flag = false;
//        try {
//            DBconn.init();
//            ResultSet rs = DBconn.selectSql("select * from user where name='" + name + "' and password='" + password + "'");
//            while (rs.next()) {
//                if (rs.getString("name").equals(name) && rs.getString("password").equals(password)) {
//                    flag = true;
//                }
//            }
//            DBconn.closeConn();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return flag;
//    }

    public List<User> getUserAll() {
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

//    public List<User> getUserAll() {
//        List<User> list = new ArrayList<User>();
//        try {
//            DBconn.init();
//            ResultSet rs = DBconn.selectSql("select * from user");
//            while (rs.next()) {
//                User user = new User();
//                user.setId(rs.getInt("id"));
//                user.setName(rs.getString("name"));
//                user.setPwd(rs.getString("password"));
//                user.setSex(rs.getString("sex"));
//                user.setHome(rs.getString("home"));
//                user.setInfo(rs.getString("info"));
//                list.add(user);
//            }
//            DBconn.closeConn();
//            return list;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//    public boolean update(int id, String name, String password, String sex, String home, String info) {
//        boolean flag = false;
//        DBconn.init();
//        String sql = "update user set name ='" + name
//                + "' , password ='" + password
//                + "' , sex ='" + sex
//                + "' , home ='" + home
//                + "' , info ='" + info + "' where id = " + id;
//        int i = DBconn.addUpdDel(sql);
//        if (i > 0) {
//            flag = true;
//        }
//        DBconn.closeConn();
//        return flag;
//    }

    public boolean update(User user) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        conn = C3P0Util.getConnection();
        try {
            ps = conn.prepareStatement("update user set name =?, password =?, sex =?, home =?, info =? where id = ?");
            ps.setString(1,user.getName());
            ps.setString(2,user.getPwd());
            ps.setString(3,user.getSex());
            ps.setString(4,user.getHome());
            ps.setString(5,user.getInfo());
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

    public boolean delete(int id) {
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