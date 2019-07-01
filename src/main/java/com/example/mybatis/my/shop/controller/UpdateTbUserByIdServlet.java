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
@WebServlet(value = "/updateTbUserById")
public class UpdateTbUserByIdServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TbUserService tbUserService=new TbUserServiceImpl();
        String id=req.getParameter("userId");
        String userName=req.getParameter("userName");
        String email=req.getParameter("userEmail");
        String passWord=req.getParameter("userPassword");
        String phone=req.getParameter("userPhone");
        TbUser tbUser=new TbUser(Long.parseLong(id),userName,passWord,phone,email);
        int i = tbUserService.updateTbUser(tbUser);
        if(i>0){
            req.setAttribute("message","修改成功！！！");
        }else{
            req.setAttribute("message","修改失败！！！");
        }
        req.getRequestDispatcher("/tbUserListServlet").forward(req,resp);
    }
}
