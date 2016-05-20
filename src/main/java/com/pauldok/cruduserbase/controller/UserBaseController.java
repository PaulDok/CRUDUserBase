package com.pauldok.cruduserbase.controller;

import com.pauldok.cruduserbase.entity.User;
import com.pauldok.cruduserbase.service.UserBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class UserBaseController {

    @Autowired
    private UserBaseService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView indexPage() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/users/count", method = RequestMethod.GET)
    public long getUserCount(@RequestParam (required = false) String name,
                             @RequestParam (required = false) int age,
                             @RequestParam (required = false) boolean admin,
                             @RequestParam (required = false) boolean searchadmin) {
        return service.getUserCount(name, age, searchadmin, admin);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAllUsers(@RequestParam int from, @RequestParam int to,
                                  @RequestParam (required = false) String name,
                                  @RequestParam (required = false) int age,
                                  @RequestParam (required = false) boolean admin,
                                  @RequestParam (required = false) boolean searchadmin) {
        return service.getAllUsers(from, to, name, age, admin, searchadmin);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public void addOrUpdateUser(@RequestBody User user) {
        service.addOrUpdateUser(user);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public void removeUser(@PathVariable int id) {
        service.removeUser(id);
    }

}
