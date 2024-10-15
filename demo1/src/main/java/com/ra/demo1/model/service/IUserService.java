package com.ra.demo1.model.service;

import com.ra.demo1.model.entity.Users;

import java.util.List;

public interface IUserService {
    List<Users> findAll(int page, int size) ;
    double totalPages(int size);
}
