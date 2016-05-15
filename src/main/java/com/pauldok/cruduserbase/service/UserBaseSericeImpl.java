package com.pauldok.cruduserbase.service;

import com.pauldok.cruduserbase.entity.User;
import com.pauldok.cruduserbase.repository.UserBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBaseSericeImpl implements UserBaseService {

    @Autowired
    private UserBaseRepository repository;

    public User addUser(User user) {
        return repository.saveAndFlush(user);
    }

    public User updateUser(User user) {
        return repository.saveAndFlush(user);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUserById(int id) {
        return repository.findOne((long)id);
    }

    public void removeUser(int id) {
        repository.delete((long) id);
    }
}
