package com.example.demo.services;

import com.example.demo.dao.ToyDAO;
import com.example.demo.dao.UserDAO;
import com.example.demo.models.Toy;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToyService {
    @Autowired
    private ToyDAO toyDAO;

    public void printToysWithMinTwoAnimals() {
        List<Toy> toys = toyDAO.getToysWithMinTwoAnimals();
        toys.forEach(toy -> System.out.println(toy.toString()));
    }
}
