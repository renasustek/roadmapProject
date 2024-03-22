package com.github.group37.roadmap.percistance;

import com.github.group37.roadmap.percistance.models.RoadmapResources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoadmapResourcesRepo extends JpaRepository<RoadmapResources, UUID> {

    @Query("SELECT u.revisionResourceId FROM RoadmapResources u WHERE u.roadmapId = ?1")
    List<UUID> findAllResourcesUsingRoadmapId(UUID roadmapId);

    @Transactional
    @Modifying
    @Query("DELETE from RoadmapResources u where u.roadmapId = ?1")
    void deleteByRoadmapId(UUID roadmapId);
}
