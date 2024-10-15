package com.ra.demo1.model.dao;

import com.ra.demo1.model.entity.Users;

import java.util.List;

public interface IUserDao {
    List<Users> findAll(int page,int size);

    long totalElement();

}
