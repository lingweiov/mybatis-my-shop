package com.example.mybatis.my.shop.entity;

import java.util.Date;

public class TbUser {
    private long  id;
    private String  userName;
    private String  passWord;
    private String  phone;
    private String  email;
    private Date  created;
    private Date updated;

    public TbUser() {
        super();
    }

    public TbUser(String userName, String passWord, String phone, String email, Date created,Date updated) {
        this.userName = userName;
        this.passWord = passWord;
        this.phone = phone;
        this.email = email;
        this.created = created;
        this.updated = updated;
    }

    public TbUser(long id, String userName, String passWord, String phone, String email) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "TbUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", phone='" + phone + '\'' +
                ", eamil='" + email + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

        public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String eamil) {
        this.email = eamil;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
