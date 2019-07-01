package com.example.mybatis.my.shop.service.impl;

import com.example.mybatis.my.shop.entity.TbUser;
import com.example.mybatis.my.shop.mapper.TbuserMapper;
import com.example.mybatis.my.shop.service.TbUserService;
import com.example.mybatis.my.shop.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class TbUserServiceImpl implements TbUserService {
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    TbuserMapper tbuserMapper = sqlSession.getMapper(TbuserMapper.class);

    @Override
    public void insertTbUser(TbUser tbUser) {
        tbuserMapper.insertTbUser(tbUser);
        sqlSession.commit();
    }

    @Override
    public List<TbUser> searchByTbUser(Map<String, String> map) {
        return tbuserMapper.searchByTbUser(map);
    }

    @Override
    public int delListTbUserById(String[]  ids) {
        int i = tbuserMapper.delListTbUserById(ids);
        sqlSession.commit();
        return i;
    }

    @Override
    public TbUser selectByEmail(String email) {
        return tbuserMapper.selectByEmail(email);
    }

    @Override
    public List<TbUser> findAllTbUsers() {
        return tbuserMapper.findAllTbUsers();
    }

    @Override
    public int delTbUserById(long id) {
        int i=tbuserMapper.delTbUserById(id);
        sqlSession.commit();
        return i;
    }

    @Override
    public TbUser findById(long id) {
        return tbuserMapper.findById(id);
    }

    @Override
    public int updateTbUser(TbUser tbUser) {
        int i=tbuserMapper.updateTbUser(tbUser);
        sqlSession.commit();
        return i;
    }
}
