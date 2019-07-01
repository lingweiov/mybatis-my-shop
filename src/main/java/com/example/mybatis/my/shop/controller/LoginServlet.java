package com.example.mybatis.my.shop.controller;

import com.example.mybatis.my.shop.entity.TbUser;
import com.example.mybatis.my.shop.service.TbUserService;
import com.example.mybatis.my.shop.service.impl.TbUserServiceImpl;
import com.example.mybatis.my.shop.utils.CookieUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/loginServlet")
public class LoginServlet extends HttpServlet {
    private static final String COOKIE_NAME = "userInfo";
    TbUserService tbUserService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        tbUserService = new TbUserServiceImpl();
        String cookieValue = CookieUtils.getCookieValue(req, COOKIE_NAME);
        String[] split = StringUtils.split(cookieValue, ":");
        if (StringUtils.isNotBlank(cookieValue)) {
            req.setAttribute("email", split[0]);
            req.setAttribute("password", split[1]);
            req.setAttribute("rememberMe","checked");
        }
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        tbUserService = new TbUserServiceImpl();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        TbUser tbUser = tbUserService.selectByEmail(email);
        String rememberMe = req.getParameter("rememberMe");
        System.out.println("======" + rememberMe);
        System.out.println(DigestUtils.md5Hex(password));
        if (!"on".equals(rememberMe)) {
            CookieUtils.deleteCookie(req, resp, COOKIE_NAME);
        }
        if (tbUser != null) {
            if (password.equals(tbUser.getPassWord())) {
                if (StringUtils.isNotBlank(rememberMe) && "on".equals(rememberMe)) {
                    req.setAttribute("rememberMe","rememberMe");
                    CookieUtils.setCookie(req, resp, COOKIE_NAME, String.format("%s:%s", email, password), 60 * 60);
                }
                req.getSession().setAttribute("TbUser",tbUser);
                resp.sendRedirect("main.jsp");
            } else {
                req.setAttribute("msg", "密码错误！！！");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("msg", "用户名或者密码错误，请重新登录！！！");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
