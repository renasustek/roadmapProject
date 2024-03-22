package com.github.group37.roadmap.service;

import com.github.group37.roadmap.percistance.RevisionResourcesRepo;
import com.github.group37.roadmap.percistance.models.RevisionResourceDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RevisionResourcesService {

    private final RevisionResourcesRepo revisionResourcesRepo;

    public RevisionResourcesService(RevisionResourcesRepo revisionResourcesRepo) {
        this.revisionResourcesRepo = revisionResourcesRepo;
    }

    public List<RevisionResourceDao> getRevisionResourceUsingTopicId(UUID topicId) {
        return revisionResourcesRepo.getRevisionResourceDaoByTopicId(topicId);
    }
}
