package com.pauldok.cruduserbase.service;

import com.pauldok.cruduserbase.entity.User;

import java.util.List;

public interface UserBaseService {
    User addOrUpdateUser(User user);
    List<User> getAllUsers(int fromIndex, int toIndex, String name, int age, boolean searchAdmin, boolean admin);
    long getUserCount(String name, int age, boolean searchAdmin, boolean admin);
    void removeUser(int id);
}
