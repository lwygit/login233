/**
 * 项目使用了单例模式，此文件无用 2020.7.6
 */
package com.dao;

import java.util.List;

import com.entity.User;

public interface UserDao {


    public boolean login(String name, String password);//登录

    public boolean register(User user);//注册

    public List<User> getUserAll();//返回用户信息集合

    public boolean delete(int id);//根据id删除用户

    //public boolean update(int id, String name, String password, String sex, String home, String info);//更新用户信息
    public boolean update(User user);//更新用户信息
}