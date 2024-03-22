package com.github.group37.roadmap.percistance;

import com.github.group37.roadmap.percistance.models.RoadmapDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoadmapRepo extends JpaRepository<RoadmapDao, UUID> {

    @Query("SELECT u FROM RoadmapDao u WHERE u.name = ?1")
    List<RoadmapDao> findByUsername(String username);
}
