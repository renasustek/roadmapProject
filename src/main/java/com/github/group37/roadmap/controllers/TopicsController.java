package com.github.group37.roadmap.controllers;

import com.github.group37.roadmap.percistance.models.TopicDao;
import com.github.group37.roadmap.service.TopicsService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/topics", produces = MediaType.APPLICATION_JSON_VALUE)
public class TopicsController {

    private final TopicsService topicsService;

    public TopicsController(TopicsService topicsService) {
        this.topicsService = topicsService;
    }

    @GetMapping("/{subjectId}")
    public List<TopicDao> getTopicsRelatedToSubject(@PathVariable UUID subjectId) {
        return topicsService.getTopicsUsingSubjectId(subjectId);
    }

    //    @PostMapping(value = "/{username}", consumes = MediaType.APPLICATION_JSON_VALUE)
    //    public ArrayList<UserTopicsDao> create(
    //            @PathVariable String username, @RequestBody UserCreateRoadmapRequest userCreateRoadmapRequest) {
    //        return topicsService.postUserTopics(username, userCreateRoadmapRequest);
    //    }
}
