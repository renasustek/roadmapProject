package com.github.group37.roadmap.other;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class UserTopicsId implements Serializable {
    private String username;
    private UUID topicId;
    private UUID roadmapId;

    public UserTopicsId() {}

    public UserTopicsId(String username, UUID topicId, UUID roadmapId) {
        this.username = username;
        this.topicId = topicId;
        this.roadmapId = roadmapId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTopicsId that = (UserTopicsId) o;
        return Objects.equals(username, that.username)
                && Objects.equals(topicId, that.topicId)
                && Objects.equals(roadmapId, that.roadmapId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, topicId, roadmapId);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UUID getTopicId() {
        return topicId;
    }

    public void setTopicId(UUID topicId) {
        this.topicId = topicId;
    }

    public UUID getRoadmapId() {
        return roadmapId;
    }

    public void setRoadmapId(UUID roadmapId) {
        this.roadmapId = roadmapId;
    }
}
