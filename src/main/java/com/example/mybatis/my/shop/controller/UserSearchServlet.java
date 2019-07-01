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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/userSearchServlet")
public class UserSearchServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search=req.getParameter("search");
        String userName=req.getParameter("userName");
        String phone=req.getParameter("phone");
        String email=req.getParameter("email");
        TbUserService tbUserService=new TbUserServiceImpl();
        Map<String, String> map=new HashMap<>();
        map.put("search",search);
        map.put("userName",userName);
        map.put("phone",phone);
        map.put("email",email);
        List<TbUser> tbUsers = tbUserService.searchByTbUser(map);
        req.setAttribute("allTbUsers",tbUsers);
        req.getRequestDispatcher("tbUserList.jsp").forward(req,resp);
    }
}
