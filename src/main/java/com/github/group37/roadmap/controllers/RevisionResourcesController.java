package com.github.group37.roadmap.controllers;

import com.github.group37.roadmap.percistance.models.RevisionResourceDao;
import com.github.group37.roadmap.service.RevisionResourcesService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/revision-resources", produces = MediaType.APPLICATION_JSON_VALUE)
public class RevisionResourcesController {

    private final RevisionResourcesService revisionResourcesService;

    public RevisionResourcesController(RevisionResourcesService revisionResourcesService) {
        this.revisionResourcesService = revisionResourcesService;
    }

    @GetMapping("/{topicId}")
    public List<RevisionResourceDao> getRevisionResourcesUsingTopicId(@PathVariable UUID topicId) {
        return revisionResourcesService.getRevisionResourceUsingTopicId(
                topicId); // todo valid uuid, nothing found should return corresponding error message
    } // todo tests failing because of poor error handling
}
