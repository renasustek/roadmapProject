package com.github.group37.roadmap.other;

import com.github.group37.roadmap.other.enums.LevelOfExpertise;

import java.util.UUID;

public record UserTopic(UUID topicId, LevelOfExpertise levelOfExpertise) {}
