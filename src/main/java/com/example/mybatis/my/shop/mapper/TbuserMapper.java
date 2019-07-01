package com.example.mybatis.my.shop.mapper;

import com.example.mybatis.my.shop.entity.TbUser;

import java.util.List;
import java.util.Map;

public interface TbuserMapper {
    int delListTbUserById(String[] ids);

    TbUser findById(long id);

    List<TbUser> findAllTbUsers();

    int delTbUserById(long id);

    int updateTbUser(TbUser tbUser);

    void insertTbUser(TbUser tbUser);

    List<TbUser> searchByTbUser(Map<String, String> map);

    TbUser selectByEmail(String email);
}
