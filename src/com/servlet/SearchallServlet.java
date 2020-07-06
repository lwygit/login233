package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;
import com.dao.UserDao1;
import com.dao.UserDaoImpl;
import com.entity.User;

public class SearchallServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        UserDao ud = new UserDaoImpl();
        UserDao1.getInstance();
        List<User> userAll = UserDao1.getUserAll();
        request.setAttribute("user", userAll);
        request.getRequestDispatcher("/success.jsp").forward(request, response);
    }
}