package com.example.mybatis.my.shop.controller;

import com.example.mybatis.my.shop.entity.TbUser;
import com.example.mybatis.my.shop.service.TbUserService;
import com.example.mybatis.my.shop.service.impl.TbUserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/tbUserListServlet")
public class TbUserListServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TbUserService tbUserService = new TbUserServiceImpl();
        List<TbUser> allTbUsers = tbUserService.findAllTbUsers();
        req.setAttribute("allTbUsers", allTbUsers);
        req.getRequestDispatcher("tbUserList.jsp").forward(req, resp);
    }
}
