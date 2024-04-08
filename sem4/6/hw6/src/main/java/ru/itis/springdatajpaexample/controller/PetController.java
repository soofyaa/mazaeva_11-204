package ru.itis.springdatajpaexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

import ru.itis.springdatajpaexample.dto.PetDto;
import ru.itis.springdatajpaexample.repository.PetRepository;

@RestController
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @GetMapping("/pets/findByAgeRangeAndName")
    public List<PetDto> getPets(@RequestParam(name = "start") Integer start, @RequestParam(name = "end") Integer end, @RequestParam(name = "name") String name) {
        return petRepository.findByAgeBetweenAndName(start, end, name)
                .stream()
                .map(PetDto::fromEntity)
                .collect(Collectors.toList());
    }
}
