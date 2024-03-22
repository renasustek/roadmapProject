package com.github.group37.roadmap.controllers;

import com.github.group37.roadmap.percistance.models.SubjectsDao;
import com.github.group37.roadmap.service.SubjectsService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/subjects", produces = MediaType.APPLICATION_JSON_VALUE)
public class SubjectsController {

    private final SubjectsService subjectsService;

    public SubjectsController(SubjectsService subjectsService) {
        this.subjectsService = subjectsService;
    }

    @GetMapping
    public List<SubjectsDao> getSubjects(){
        return subjectsService.getSubjects();
    }
}
