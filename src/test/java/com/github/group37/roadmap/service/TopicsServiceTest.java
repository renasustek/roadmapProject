package com.github.group37.roadmap.service;

import com.github.group37.roadmap.percistance.TopicsRepo;
import com.github.group37.roadmap.percistance.models.RevisionResourceDao;
import com.github.group37.roadmap.percistance.models.TopicDao;
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
class TopicsServiceTest {
    @Mock
    public TopicsRepo topicsRepo;

    @InjectMocks
    private TopicsService topicsService;

    private UUID validUuid = UUID.randomUUID();
    private List<TopicDao> topicDaoList = List.of(topicDao());


    private TopicDao topicDao() {
        TopicDao topicDao = new TopicDao();
        topicDao.setId(validUuid);
        topicDao.setTopicName("TESTNAME");
        topicDao.setSubject(validUuid);
        return topicDao;
    }

    @DisplayName("valid uuid returns list of topics")
    @Test
    void when_given_valid_uuid_should_return_list_of_topic() {
        given(topicsRepo.findTopicsUsingSubjectId(validUuid)).willReturn(topicDaoList);
        List<TopicDao> serviceTest = topicsService.getTopicsUsingSubjectId(validUuid);
        assertThat(serviceTest.get(0).getTopicName()).isEqualTo(topicDao().getTopicName());
        assertThat(serviceTest.get(0).getSubject()).isEqualTo(topicDao().getId());
    }

    @DisplayName("uuid isnt in database- empty list")
    @Test
    void when_given_INvalid_uuid_should_return_empty_list() {
        given(topicsRepo.findTopicsUsingSubjectId(validUuid)).willReturn(List.of());

        List<TopicDao> serviceTest = topicsService.getTopicsUsingSubjectId(validUuid);

        assertThat(serviceTest.isEmpty());
    }

}