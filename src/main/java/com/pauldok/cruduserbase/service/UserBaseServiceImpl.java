package com.pauldok.cruduserbase.service;

import com.pauldok.cruduserbase.entity.User;
import com.pauldok.cruduserbase.repository.UserBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBaseServiceImpl implements UserBaseService {

    @Autowired
    private UserBaseRepository repository;

    public User addOrUpdateUser(User user) {
        return repository.saveAndFlush(user);
    }

    public List<User> getAllUsers(int fromIndex, int toIndex, String name, int age, boolean searchAdmin, boolean admin) {
        List<User> allFilteredUsers = filteredList(name, age, searchAdmin, admin);
        return allFilteredUsers.subList(fromIndex - 1, toIndex);
    }

    public long getUserCount(String name, int age, boolean searchAdmin, boolean admin) {
        return filteredList(name, age, searchAdmin, admin).size();
    }

    public void removeUser(int id) {
        repository.delete(id);
    }

    private List<User> filteredList(String name, int age, boolean searchAdmin, boolean admin) {
        List<User> filteredList = repository.findAll();
        if (name != null) {
            for (int i = filteredList.size() - 1; i >= 0; i--) {
                if (!filteredList.get(i).getName().toLowerCase().contains(name.toLowerCase()))
                    filteredList.remove(i);
            }
        }
        if (age != -1) {
            for (int i = filteredList.size() - 1; i >= 0; i--) {
                if (filteredList.get(i).getAge() != age)
                    filteredList.remove(i);
            }
        }
        if (searchAdmin) {
            for (int i = filteredList.size() - 1; i >= 0; i--) {
                if (filteredList.get(i).isAdmin() != admin)
                    filteredList.remove(i);
            }
        }
        return filteredList;
    }
}
