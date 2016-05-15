package com.pauldok.cruduserbase.service;

import com.pauldok.cruduserbase.entity.User;

import java.util.List;

public interface UserBaseService {
    User addUser(User user);
    User updateUser(User user);
    List<User> getAllUsers();
    User getUserById(int id);
    void removeUser(int id);
}
