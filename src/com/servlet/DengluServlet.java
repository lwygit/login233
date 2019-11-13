package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDao;
import com.dao.UserDaoImpl;
import com.google.gson.Gson;


public class DengluServlet extends HttpServlet {  //需要继承HttpServlet  并重写doGet  doPost方法
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);  //将信息使用doPost方法执行   对应jsp页面中的form表单中的method
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String name1 = (String)session.getAttribute("name");
        System.out.println("name1:"+name1);
        String name = request.getParameter("name"); //得到jsp页面传过来的参数
        String password = request.getParameter("password");
        System.out.println(name + "" + password);
        UserDao ud = new UserDaoImpl();
        Gson gson = new Gson();

        if (ud.login(name, password)) {
            session.setAttribute("name1", "longestory");
            request.setAttribute("xiaoxi", "欢迎用户" + name); //向request域中放置信息
            request.getRequestDispatcher("/success.jsp").forward(request, response);//转发到成功页面
        } else {
            response.sendRedirect("failure.jsp"); //重定向到首页
        }
    }

}