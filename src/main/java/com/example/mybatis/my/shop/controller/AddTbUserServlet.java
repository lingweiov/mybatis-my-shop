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
import java.util.Date;

@WebServlet(value = "/addTbUserServlet")
public class AddTbUserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TbUserService tbUserService=new TbUserServiceImpl();
        String userName=req.getParameter("userName");
        String email=req.getParameter("userEmail");
        String passWord=req.getParameter("userPassword");
        String phone=req.getParameter("userPhone");
        TbUser tbUser=new TbUser(userName,passWord,phone,email,new Date(),new Date());
        tbUserService.insertTbUser(tbUser);
        req.getRequestDispatcher("/tbUserListServlet").forward(req,resp);
    }
}
