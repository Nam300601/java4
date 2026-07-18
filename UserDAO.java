package com.lab1.dao;



import java.util.List;
import com.lab1.entity.User; // Cập nhật package ở đây

public interface UserDAO {
    List<User> findAll();
    User findById(String id);
    void create(User item);
    void update(User item);
    void deleteById(String id);
}