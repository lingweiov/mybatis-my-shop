package com.example.mybatis.my.shop.controller;

import com.example.mybatis.my.shop.service.TbUserService;
import com.example.mybatis.my.shop.service.impl.TbUserServiceImpl;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(value = "/deleteTbUserById")
public class DelTbUserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(StringUtils.isNotBlank(id)){
            TbUserService tbUserService=new TbUserServiceImpl();
            int i = tbUserService.delTbUserById(Long.parseLong(id));
            if(i>0){
                req.setAttribute("message","删除成功！！！");
            }else{
                req.setAttribute("message","删除失败！！！");
            }
        }
        req.getRequestDispatcher("/tbUserListServlet").forward(req,resp);
    }
}
