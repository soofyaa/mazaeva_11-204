package com.example.demo.services;

import com.example.demo.dao.UserDAO;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public void printPeopleWithToys() {
        List<User> people = userDAO.findPeopleWithToys();
        people.forEach(user -> System.out.println(user.toString()));
    }
}
