package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;
import com.dao.UserDaoImpl;
import com.entity.User;

public class UpdateServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = new User(); //实例化一个对象，组装属性
        user.setId(Integer.parseInt(request.getParameter("id")));
        user.setName(request.getParameter("name"));
        user.setPwd(request.getParameter("password"));
        user.setSex(request.getParameter("sex"));
        user.setHome(request.getParameter("home"));
        user.setInfo(request.getParameter("info"));

//        int userId = Integer.parseInt(request.getParameter("id"));
//
//        String name = request.getParameter("name");
//        String password = request.getParameter("password");
//        String sex = request.getParameter("sex");
//        String home = request.getParameter("home");
//        String info = request.getParameter("info");

        System.out.println("id == "+user.getId());

        UserDao ud = new UserDaoImpl();

        if(ud.update(user)){
            request.setAttribute("xiaoxi", "更新成功");
            request.getRequestDispatcher("/SearchallServlet").forward(request, response);
        }else{
            response.sendRedirect("failure.jsp");
        }
    }
}