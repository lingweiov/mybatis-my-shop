package com.example.mybatis.my.shop.controller;

import com.example.mybatis.my.shop.entity.TbUser;
import com.example.mybatis.my.shop.service.TbUserService;
import com.example.mybatis.my.shop.service.impl.TbUserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/findTbUserByIdServlet")
public class FindTbUserByIdServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TbUserService tbUserService = new TbUserServiceImpl();
        resp.setCharacterEncoding("utf-8");
        String id=req.getParameter("id");
        if (StringUtils.isNotBlank(id)) {
            TbUser tbUser = tbUserService.findById(Long.parseLong(id));
            ObjectMapper objectMapper=new ObjectMapper();
            String jSonData = objectMapper.writeValueAsString(tbUser);
            PrintWriter out=resp.getWriter();
            out.print(jSonData);
            System.out.println(jSonData);
        }
    }
}