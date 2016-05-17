package com.pauldok.cruduserbase.controller;

import com.pauldok.cruduserbase.entity.User;
import com.pauldok.cruduserbase.service.UserBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@RestController
public class UserBaseController {

    @Autowired
    private UserBaseService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView indexPage() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    private User getMockUser() {
        User user = new User();
        user.setId(5);
        user.setName("Paul");
        user.setAge(29);
        user.setAdmin(true);
        user.setCreatedDate(new Date());
        return user;
    }
}
