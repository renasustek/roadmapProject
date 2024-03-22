package com.github.group37.roadmap.percistance.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "roadmap_resources", schema = "roadmap_project")
public class RoadmapResources {

    @Id
    @Column(name = "id", columnDefinition = "VARCHAR(36)", nullable = false, unique = true, length = 36)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(name = "roadmap_id", columnDefinition = "VARCHAR(36)", nullable = false, length = 36)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID roadmapId;

    @Column(name = "revision_resource_id", columnDefinition = "VARCHAR(36)", nullable = false, length = 36)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID revisionResourceId;

    public RoadmapResources(UUID id, UUID roadmapId, UUID revisionResourceId) {
        this.id = id;
        this.roadmapId = roadmapId;
        this.revisionResourceId = revisionResourceId;
    }

    public RoadmapResources() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getRoadmapId() {
        return roadmapId;
    }

    public void setRoadmapId(UUID roadmapId) {
        this.roadmapId = roadmapId;
    }

    public UUID getRevisionResourceId() {
        return revisionResourceId;
    }

    public void setRevisionResourceId(UUID revisionResourceId) {
        this.revisionResourceId = revisionResourceId;
    }
}
