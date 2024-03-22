package com.github.group37.roadmap.percistance;

import com.github.group37.roadmap.other.enums.LevelOfExpertise;
import com.github.group37.roadmap.percistance.models.RevisionResourceDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RevisionResourcesRepo extends JpaRepository<RevisionResourceDao, UUID> {
    @Query("select r FROM RevisionResourceDao r where r.topic = ?1")
    List<RevisionResourceDao> getRevisionResourceDaoByTopicId(UUID topicId);

    @Query("select r FROM RevisionResourceDao r where r.topic = ?1 and r.levelOfExpertise<=?2")
    List<RevisionResourceDao> getRevisionResources(UUID topicId, LevelOfExpertise levelOfExpertise);
}
