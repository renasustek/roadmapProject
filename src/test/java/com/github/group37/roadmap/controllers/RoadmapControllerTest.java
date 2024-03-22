package com.github.group37.roadmap.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.group37.roadmap.other.Roadmap;
import com.github.group37.roadmap.other.UpdatedRoadmapName;
import com.github.group37.roadmap.other.UserCreateRoadmapRequest;
import com.github.group37.roadmap.other.UserTopic;
import com.github.group37.roadmap.other.enums.LevelOfExpertise;
import com.github.group37.roadmap.percistance.models.RevisionResourceDao;
import com.github.group37.roadmap.percistance.models.RoadmapDao;
import com.github.group37.roadmap.service.RoadmapService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = RoadmapController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
class RoadmapControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoadmapService service;

    @Autowired
    private ObjectMapper objectMapper;

    private final String username = "name";

    private UUID roadmapId = UUID.randomUUID();
    private final Roadmap roadmap = new Roadmap("name", roadmapId, new ArrayList<Optional<RevisionResourceDao>>());

    @DisplayName("valid username")
    @Test
    void when_given_valid_username_should_return_a_roadmap_succsesfully() throws Exception {

        when(service.getRoadmap(username)).thenReturn(List.of(roadmap));
        this.mockMvc
                .perform(get("/roadmap/" + username))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].name").value(roadmap.getName()))
                .andExpect(jsonPath("$[0].revisionResourceDaos").value(roadmap.getRevisionResourceDaos()));
    }

    @DisplayName("invalid username")
    @Test
    void when_invalid_username_used_should_return_roadmap_not_found() throws Exception {
        when(service.getRoadmap(username)).thenReturn(Collections.emptyList());
        this.mockMvc
                .perform(get("/roadmap/" + username))
                .andExpect(status().is2xxSuccessful()) // todo make sure it returns no roadmaps found in the
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json("[]", true));
    }

    @DisplayName("no roadmap found for existing user")
    @Test
    void when_valid_username_and_no_roadmap_found_roadmap_not_found_return_empty_list() throws Exception {
        when(service.getRoadmap(username)).thenReturn(Collections.emptyList());
        this.mockMvc
                .perform(get("/roadmap/" + username))
                .andExpect(status().is2xxSuccessful()) // todo make sure it returns no roadmaps found in the
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json("[]", true));
    }

    //    @DisplayName("POST - given a username that already has a generated roadmap")
    //    @Test
    //    void when_given_username_that_already_has_a_created_roadmap_throw_exception() throws Exception {
    //        when(service.createRoadmap(username)).thenReturn(Optional.empty());
    //        this.mockMvc.perform(post("/roadmap/" + username)).andExpect(status().isBadRequest());
    //    }

    @DisplayName("POST - Successfully create a new roadmap")
    @Test
    void when_given_username_and_roadmap_details_should_create_roadmap_successfully() throws Exception {
        UserCreateRoadmapRequest request = new UserCreateRoadmapRequest();
        request.setRoadmapName("name");
        request.setUserTopics(List.of(new UserTopic(UUID.randomUUID(), LevelOfExpertise.EXPERT)));

        when(service.createRoadmap(username, request)).thenReturn(Optional.of(roadmap));

        this.mockMvc
                .perform(post("/roadmap/" + username)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(roadmap.getName()))
                .andExpect(jsonPath("$.id").value(roadmap.getId()));
    }

    @DisplayName("PUT - Update existing roadmap's name")
    @Test
    void when_given_roadmap_id_and_name_should_update_name() throws Exception {
        UpdatedRoadmapName updatedName = new UpdatedRoadmapName("Updated Name");
        RoadmapDao updatedRoadmap = new RoadmapDao(roadmapId, username, updatedName.name());

        when(service.editRoadmapName(roadmapId, updatedName)).thenReturn(Optional.of(updatedRoadmap));

        this.mockMvc
                .perform(put("/roadmap/" + roadmapId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedName)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roadmapName").value(updatedName.name()));
    }
}