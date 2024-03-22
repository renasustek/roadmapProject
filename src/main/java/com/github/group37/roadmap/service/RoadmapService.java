package com.github.group37.roadmap.service;

import com.github.group37.roadmap.other.Roadmap;
import com.github.group37.roadmap.other.UpdatedRoadmapName;
import com.github.group37.roadmap.other.UserCreateRoadmapRequest;
import com.github.group37.roadmap.other.UserTopic;
import com.github.group37.roadmap.percistance.RevisionResourcesRepo;
import com.github.group37.roadmap.percistance.RoadmapRepo;
import com.github.group37.roadmap.percistance.RoadmapResourcesRepo;
import com.github.group37.roadmap.percistance.UserTopicsRepo;
import com.github.group37.roadmap.percistance.models.RevisionResourceDao;
import com.github.group37.roadmap.percistance.models.RoadmapDao;
import com.github.group37.roadmap.percistance.models.RoadmapResources;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoadmapService {
    public final RoadmapRepo roadmapRepo;
    public final RoadmapResourcesRepo roadmapResourcesRepo;
    public final RevisionResourcesRepo revisionResourcesRepo;

    private final TopicsService topicsService;

    private final UserTopicsRepo userTopicsRepo;

    public RoadmapService(
            RoadmapRepo roadmapRepo,
            RoadmapResourcesRepo roadmapResourcesRepo,
            RevisionResourcesRepo revisionResourcesRepo,
            TopicsService topicsService,
            UserTopicsRepo userTopicsRepo) {
        this.roadmapRepo = roadmapRepo;
        this.roadmapResourcesRepo = roadmapResourcesRepo;
        this.revisionResourcesRepo = revisionResourcesRepo;
        this.topicsService = topicsService;
        this.userTopicsRepo = userTopicsRepo;
    }

    public List<Roadmap> getRoadmap(String username) {
        List<RoadmapDao> allUserRoadmapIds = roadmapRepo.findByUsername(username);
        if (allUserRoadmapIds.isEmpty()) {
            return Collections.emptyList();
        }
        List<Roadmap> AllRoadmaps = new ArrayList<>();

        allUserRoadmapIds.forEach(roadmapDao -> {
            ArrayList<Optional<RevisionResourceDao>> revisionResources = new ArrayList<>();

            roadmapResourcesRepo
                    .findAllResourcesUsingRoadmapId(roadmapDao.getId())
                    .forEach(revisionResourceId -> {
                        revisionResources.add(revisionResourcesRepo.findById(revisionResourceId));
                    });
            AllRoadmaps.add(new Roadmap(roadmapDao.getRoadmapName(), roadmapDao.getId(), revisionResources));
        });

        return AllRoadmaps;
    }

    public Optional<Roadmap> createRoadmap(String username, UserCreateRoadmapRequest userCreateRoadmapRequest) {
        ArrayList<Optional<RevisionResourceDao>> revisionResourceDaos = new ArrayList<>();

        UUID roadmapId = UUID.randomUUID();
        roadmapRepo.save(new RoadmapDao(roadmapId, username, userCreateRoadmapRequest.getRoadmapName()));

        List<UserTopic> userTopics = userCreateRoadmapRequest.getUserTopics();
        userTopics.forEach(eachUserTopic -> {
            revisionResourcesRepo
                    .getRevisionResources(eachUserTopic.topicId(), eachUserTopic.levelOfExpertise())
                    .forEach(eachRevisionResource -> {
                        RoadmapResources save = roadmapResourcesRepo.save(
                                new RoadmapResources(UUID.randomUUID(), roadmapId, eachRevisionResource.getId()));
                        revisionResourceDaos.add(Optional.of(eachRevisionResource));
                    });
        });
        topicsService.postUserTopics(username, userTopics, roadmapId);
        return Optional.of(new Roadmap(userCreateRoadmapRequest.getRoadmapName(), roadmapId, revisionResourceDaos));
    }

    public void deleteRoadmap(UUID roadmapId) {
        if (roadmapRepo.findById(roadmapId).isPresent()) {
            userTopicsRepo.deleteByRoadmapId(roadmapId);
            roadmapResourcesRepo.deleteByRoadmapId(roadmapId);
            roadmapRepo.deleteById(roadmapId);
        } // todo response entity return roadmap not found errors and what not
    }

    public Optional<RoadmapDao> editRoadmapName(UUID roadmapId, UpdatedRoadmapName updatedRoadmapName) {
        return roadmapRepo.findById(roadmapId).map(roadmap -> {
            roadmap.setRoadmapName(updatedRoadmapName.name());
            return roadmapRepo.save(roadmap);
        });
    } // todo check error handling for everything
}
