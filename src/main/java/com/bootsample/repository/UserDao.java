package com.bootsample.repository;

import org.springframework.stereotype.Repository;

import com.bootsample.entity.MyUser;

@Repository
public interface UserDao {
    MyUser findUserByUserName(String userName);
}