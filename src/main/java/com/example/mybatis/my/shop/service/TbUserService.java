package com.example.mybatis.my.shop.service;

import com.example.mybatis.my.shop.entity.TbUser;

import java.util.List;
import java.util.Map;

public interface TbUserService {
    TbUser selectByEmail(String email);

    List<TbUser> findAllTbUsers();

    int delTbUserById(long id);

    TbUser findById(long id);

    int updateTbUser(TbUser tbUser);

    void insertTbUser(TbUser tbUser);

    List<TbUser> searchByTbUser(Map<String, String> map);

    int delListTbUserById(String[]  ids);
}
