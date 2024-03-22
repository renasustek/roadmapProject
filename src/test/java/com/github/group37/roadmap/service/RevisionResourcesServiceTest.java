package com.github.group37.roadmap.service;

import com.github.group37.roadmap.other.enums.LevelOfExpertise;
import com.github.group37.roadmap.percistance.RevisionResourcesRepo;
import com.github.group37.roadmap.percistance.models.RevisionResourceDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RevisionResourcesServiceTest {
    @Mock
    public RevisionResourcesRepo revisionResourcesRepo;

    @InjectMocks
    private RevisionResourcesService revisionResourcesService;

    private UUID validUuid = UUID.randomUUID();
    private List<RevisionResourceDao> revisionResourceDaos = List.of(revisionResourceDao());

    private RevisionResourceDao revisionResourceDao() {
        RevisionResourceDao revisionResourceDao = new RevisionResourceDao();
        revisionResourceDao.setId(validUuid);
        revisionResourceDao.setResourceName("TESTNAME");
        revisionResourceDao.setDescription("TESTDESCRIPTION");
        revisionResourceDao.setTopic(validUuid);
        revisionResourceDao.setLevelOfExpertise(LevelOfExpertise.BEGINNER);
        return revisionResourceDao;
    }

    @DisplayName("valid uuid returns list")
    @Test
    void when_given_valid_uuid_should_return_list_of_revisionRecources() {
        given(revisionResourcesRepo.getRevisionResourceDaoByTopicId(validUuid)).willReturn(revisionResourceDaos);
        List<RevisionResourceDao> serviceTest = revisionResourcesService.getRevisionResourceUsingTopicId(validUuid);
        assertThat(serviceTest.get(0).getResourceName())
                .isEqualTo(revisionResourceDao().getResourceName());
        assertThat(serviceTest.get(0).getDescription())
                .isEqualTo(revisionResourceDao().getDescription());
    }

    @DisplayName("uuid isnt in database returns empty list")
    @Test
    void when_given_INvalid_uuid_should_return_empty_list() {
        given(revisionResourcesRepo.getRevisionResourceDaoByTopicId(validUuid)).willReturn(List.of());

        List<RevisionResourceDao> serviceTest = revisionResourcesService.getRevisionResourceUsingTopicId(validUuid);

        assertThat(serviceTest.isEmpty());
    }
}