package com.github.group37.roadmap.service;

import com.github.group37.roadmap.other.Roadmap;
import com.github.group37.roadmap.other.UpdatedRoadmapName;
import com.github.group37.roadmap.other.UserCreateRoadmapRequest;
import com.github.group37.roadmap.other.UserTopic;
import com.github.group37.roadmap.other.enums.LevelOfExpertise;
import com.github.group37.roadmap.percistance.RevisionResourcesRepo;
import com.github.group37.roadmap.percistance.RoadmapRepo;
import com.github.group37.roadmap.percistance.RoadmapResourcesRepo;
import com.github.group37.roadmap.percistance.UserTopicsRepo;
import com.github.group37.roadmap.percistance.models.RevisionResourceDao;
import com.github.group37.roadmap.percistance.models.RoadmapDao;
import com.github.group37.roadmap.percistance.models.UserTopicsDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RoadmapServiceTest {
    @Mock
    public RoadmapRepo roadmapRepo;

    @Mock
    public RoadmapResourcesRepo roadmapResourcesRepo;

    @Mock
    public RevisionResourcesRepo revisionResourcesRepo;

    @Mock
    private UserTopicsRepo userTopicsRepo;

    @InjectMocks
    private RoadmapService roadmapService;

    @Mock
    TopicsService topicsService;

    private LevelOfExpertise levelOfExpertise = LevelOfExpertise.BEGINNER;

    private UUID topicId = UUID.randomUUID();

    private UUID roadmapId = UUID.randomUUID();

    private String username = "username";
    private RoadmapDao roadmapDao = new RoadmapDao(roadmapId, username, "roadmapName");

    private List<UUID> userRecourseId = List.of(UUID.randomUUID());

    private UserTopicsDao userTopic1 = new UserTopicsDao(
            username, UUID.fromString("22be771a-7803-445f-b88f-732fd6170f56"), LevelOfExpertise.BEGINNER, roadmapId);
    private UserTopicsDao userTopic2 = new UserTopicsDao(
            username, UUID.fromString("1915b4be-7f11-48bb-97ff-88f9297104f8"), LevelOfExpertise.BEGINNER, roadmapId);
    private List<UserTopicsDao> userTopicsDaos = List.of(userTopic1);

    private Optional<List<UUID>> generateIds(int size) {
        Random rand = new Random();
        //        int x = rand.nextInt(max - min + 1) + min;
        List<UUID> uuidList = new ArrayList<>();
        for (int y = 0; y < size; y++) {
            uuidList.add(UUID.randomUUID());
        }
        return Optional.of(uuidList);
    }

    @DisplayName("valid username returns roadmap")
    @Test
    void given_valid_username_roadmap_belonging_to_user_returned_succesfully() {

        given(roadmapRepo.findByUsername(username)).willReturn(List.of(roadmapDao));

        given(roadmapResourcesRepo.findAllResourcesUsingRoadmapId(roadmapId)).willReturn(userRecourseId);
        RevisionResourceDao revisionResourceDao = new RevisionResourceDao();
        revisionResourceDao.setId(UUID.randomUUID());
        revisionResourceDao.setResourceName("TEST_NAME");
        revisionResourceDao.setDescription("TEST_DESCRIPTION");
        revisionResourceDao.setTopic(UUID.randomUUID());
        revisionResourceDao.setWhereToAccess("TEST_WHERE_TO_ACCESS");
        given(revisionResourcesRepo.findById(userRecourseId.get(0))).willReturn(Optional.of(revisionResourceDao));

        List<Roadmap> roadmapServiceTest = roadmapService.getRoadmap(username);

        ArrayList<Optional<RevisionResourceDao>> revisionResources = new ArrayList<>();

        revisionResources.add(Optional.of(revisionResourceDao));

        Optional<Roadmap> roadmapTest = Optional.of(new Roadmap(username, roadmapId, revisionResources));

        assertThat(roadmapServiceTest.get(0).getRevisionResourceDaos())
                .isEqualTo(roadmapTest.get().getRevisionResourceDaos());
    }

    @Test
    void given_valid_username_when_no_roadmap_returns_empty_optional() {

        given(roadmapRepo.findByUsername(username)).willReturn(Collections.emptyList());

        List<Roadmap> roadmapServiceTest = roadmapService.getRoadmap(username);

        assertThat(roadmapServiceTest).isEqualTo(Collections.emptyList());
    }

    @Test
    void no_revision_recources_found_should_still_return_roadmap() {

        given(roadmapRepo.findByUsername(username)).willReturn(List.of(roadmapDao));
        given(roadmapResourcesRepo.findAllResourcesUsingRoadmapId(roadmapId)).willReturn(userRecourseId);

        given(revisionResourcesRepo.findById(userRecourseId.get(0))).willReturn(Optional.empty());

        List<Roadmap> roadmapServiceTest = roadmapService.getRoadmap(username);

        ArrayList<Optional<RevisionResourceDao>> revisionRecources = new ArrayList<>();
        revisionRecources.add(Optional.empty());
        Optional<Roadmap> roadmapTest = Optional.of(new Roadmap(username, roadmapId, revisionRecources));

        assertThat(roadmapServiceTest.get(0).getRevisionResourceDaos())
                .isEqualTo(roadmapTest.get().getRevisionResourceDaos());
    }

    @Test
    void given_invalid_username_should_return_empty_roadmap() {
        given(roadmapRepo.findByUsername(username)).willReturn(Collections.emptyList());

        List<Roadmap> roadmapServiceTest = roadmapService.getRoadmap(username);

        assertThat(roadmapServiceTest).isEqualTo(Collections.emptyList());
    }

    @Test
    @DisplayName("Create roadmap successfully with valid username and request")
    void given_username_and_request_then_create_roadmap_success() {
        UserCreateRoadmapRequest userCreateRoadmapRequest = new UserCreateRoadmapRequest();
        userCreateRoadmapRequest.setRoadmapName("Test Roadmap");
        userCreateRoadmapRequest.setUserTopics(List.of(new UserTopic(topicId, levelOfExpertise)));

        RevisionResourceDao revisionResourceDao = new RevisionResourceDao();
        revisionResourceDao.setId(UUID.randomUUID());
        revisionResourceDao.setResourceName("Resource Name");
        revisionResourceDao.setDescription("Resource Description");
        revisionResourceDao.setTopic(topicId);

        given(revisionResourcesRepo.getRevisionResources(eq(topicId), eq(levelOfExpertise)))
                .willReturn(List.of(revisionResourceDao));

        Optional<Roadmap> createdRoadmap = roadmapService.createRoadmap(username, userCreateRoadmapRequest);

        assertTrue(createdRoadmap.isPresent());
        assertEquals(
                userCreateRoadmapRequest.getRoadmapName(), createdRoadmap.get().getName());
        assertEquals(1, createdRoadmap.get().getRevisionResourceDaos().size());
        assertEquals(
                revisionResourceDao,
                createdRoadmap.get().getRevisionResourceDaos().get(0).orElse(null));
    }

    //    @DisplayName("username is found in database, so the user already has a roadmap")
    //    @Test
    //    void given_user_has_roadmap_return_optional_empty() {
    //        given(roadmapRepo.findByUsername(username)).willReturn(Optional.of(UUID.randomUUID()));
    //        assertTrue(roadmapService.createRoadmap(username).isEmpty());
    //    }

    @Test
    @DisplayName("Successfully delete a roadmap if it exists")
    void when_delete_roadmap_if_roadmap_exists_then_success() {
        UUID roadmapId = UUID.randomUUID();

        given(roadmapRepo.findById(roadmapId)).willReturn(Optional.of(new RoadmapDao()));

        roadmapService.deleteRoadmap(roadmapId);
    }

    @Test
    @DisplayName("Successfully edit roadmap name if roadmap is found")
    void when_edit_roadmapName_if_roadmap_found_then_editName() {
        UUID roadmapId = UUID.randomUUID();
        UpdatedRoadmapName updatedRoadmapName = new UpdatedRoadmapName("New Name");
        RoadmapDao existingRoadmap = new RoadmapDao();
        existingRoadmap.setId(roadmapId);
        existingRoadmap.setRoadmapName("Old Name");

        given(roadmapRepo.findById(roadmapId)).willReturn(Optional.of(existingRoadmap));
        existingRoadmap.setRoadmapName(updatedRoadmapName.name());
        given(roadmapRepo.save(existingRoadmap)).willReturn(existingRoadmap);

        Optional<RoadmapDao> updatedRoadmap = roadmapService.editRoadmapName(roadmapId, updatedRoadmapName);

        assertTrue(updatedRoadmap.isPresent());
        assertEquals("New Name", updatedRoadmap.get().getRoadmapName());
    }
}
