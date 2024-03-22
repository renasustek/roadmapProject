package com.github.group37.roadmap.other;

import java.util.List;

public class UserCreateRoadmapRequest {

    private String roadmapName;
    private List<UserTopic> userTopics;

    public String getRoadmapName() {
        return roadmapName;
    }

    public void setRoadmapName(String roadmapName) {
        this.roadmapName = roadmapName;
    }

    public List<UserTopic> getUserTopics() {
        return userTopics;
    }

    public void setUserTopics(List<UserTopic> userTopics) {
        this.userTopics = userTopics;
    }
}
