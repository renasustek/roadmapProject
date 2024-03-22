package com.github.group37.roadmap.percistance.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "roadmap", schema = "roadmap_project")
public class RoadmapDao {

    @Id
    @Column(name = "id", columnDefinition = "VARCHAR(36)", nullable = false, unique = true, length = 36)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(name = "username", nullable = false, unique = true, length = 36)
    @Size(min = 3, max = 36)
    private String name;

    @Column(name = "roadmap_name", nullable = true, unique = false, length = 12)
    @Size(min = 1, max = 12)
    private String roadmapName;

    public RoadmapDao(UUID id, String name, String roadmapName) {
        this.id = id;
        this.name = name;
        this.roadmapName = roadmapName;
    }

    public RoadmapDao() {}

    public String getRoadmapName() {
        return roadmapName;
    }

    public void setRoadmapName(String roadmap_name) {
        this.roadmapName = roadmap_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
