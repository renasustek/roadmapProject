package com.github.group37.roadmap.percistance.models;

import com.github.group37.roadmap.other.UserTopicsId;
import com.github.group37.roadmap.other.enums.LevelOfExpertise;
import com.github.group37.roadmap.other.enums.LevelOfExpertiseConverter;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "user_topics", schema = "roadmap_project")
@IdClass(UserTopicsId.class)
public class UserTopicsDao {

    @Id
    @Column(name = "topic_id", columnDefinition = "VARCHAR(36)", nullable = false, length = 36)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID topicId;

    @Id
    @Column(name = "roadmap_id", columnDefinition = "VARCHAR(36)", nullable = false, length = 36)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID roadmapId;

    @Id
    @Column(name = "username", columnDefinition = "VARCHAR(50)", nullable = false, length = 50)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String username;

    @Convert(converter = LevelOfExpertiseConverter.class)
    @Column(name = "level_of_expertise", columnDefinition = "VARCHAR(12)", nullable = false, length = 12)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private LevelOfExpertise levelOfExpertise;

    public UserTopicsDao(String username, UUID topicId, LevelOfExpertise levelOfExpertise, UUID roadmapId) {
        this.topicId = topicId;
        this.username = username;
        this.levelOfExpertise = levelOfExpertise;
        this.roadmapId = roadmapId;
    }

    public UserTopicsDao() {}

    public UUID getTopicId() {
        return topicId;
    }

    public void setTopicId(UUID topicId) {
        this.topicId = topicId;
    }

    public LevelOfExpertise getLevelOfExpertise() {
        return levelOfExpertise;
    }

    public void setLevelOfExpertise(LevelOfExpertise levelOfExpertise) {
        this.levelOfExpertise = levelOfExpertise;
    }

    public UUID getRoadmapId() {
        return roadmapId;
    }

    public void setRoadmapId(UUID roadmap_id) {
        this.roadmapId = roadmap_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
