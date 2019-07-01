package com.example.mybatis.my.shop.controller;

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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/allDeleteServlet")
public class AllDeleteServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TbUserService tbUserService=new TbUserServiceImpl();
        //解决json中文乱码
        resp.setContentType("text/json,charset=utf-8");

        //获取前台传递过来的数组
        String ids = req.getParameter("ids");

        int i = 0;
        if(StringUtils.isNotBlank(ids)){
            String[] idArray = StringUtils.split(ids,",");
            //批量删除
            i = tbUserService.delListTbUserById(idArray);
        }
        Map<String,Object> map = new HashMap();
        if(i>0){
            map.put("message",String.format("成功删除 %s 条数据！！！",i));
        }else {
            map.put("message","数据删除失败！！！");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonMap = objectMapper.writeValueAsString(map);

        //最后将结果输出给前台
        PrintWriter out = resp.getWriter();
        out.print(jsonMap);
        out.flush();
        out.close();
    }
}
