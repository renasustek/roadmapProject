package com.github.group37.roadmap.service;

import com.github.group37.roadmap.percistance.SubjectsRepo;
import com.github.group37.roadmap.percistance.models.SubjectsDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectsService {

    private final SubjectsRepo subjectsRepo;

    public SubjectsService(SubjectsRepo subjectsRepo) {
        this.subjectsRepo = subjectsRepo;
    }

    public List<SubjectsDao> getSubjects(){
        return subjectsRepo.findAll();
    }
}
