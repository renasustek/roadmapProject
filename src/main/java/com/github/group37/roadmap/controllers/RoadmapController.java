package com.github.group37.roadmap.controllers;

import com.github.group37.roadmap.errors.CouldntCreateRoadmap;
import com.github.group37.roadmap.other.Roadmap;
import com.github.group37.roadmap.other.UpdatedRoadmapName;
import com.github.group37.roadmap.other.UserCreateRoadmapRequest;
import com.github.group37.roadmap.percistance.models.RoadmapDao;
import com.github.group37.roadmap.service.RoadmapService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/roadmap", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoadmapController {

    public final RoadmapService roadmapService;

    public RoadmapController(RoadmapService roadmapService) {
        this.roadmapService = roadmapService;
    }

    @GetMapping("/{username}")
    public List<Roadmap> getRoadmap(@PathVariable String username) {
        return roadmapService.getRoadmap(username);
    }

    @PostMapping("/{username}")
    public Roadmap createRoadmap(
            @PathVariable String username, @RequestBody UserCreateRoadmapRequest userCreateRoadmapRequest) {
        return roadmapService
                .createRoadmap(username, userCreateRoadmapRequest)
                .orElseThrow(() -> new CouldntCreateRoadmap()); //
    }

    @DeleteMapping("/{roadmapId}")
    public void deleteRoadmap(@PathVariable UUID roadmapId) {
        roadmapService.deleteRoadmap(roadmapId);
    }

    @PutMapping("/{roadmapId}")
    public Optional<RoadmapDao> editRoadmapName(
            @PathVariable UUID roadmapId, @RequestBody UpdatedRoadmapName updatedRoadmapName) {
        return roadmapService.editRoadmapName(roadmapId, updatedRoadmapName);
    }
}
