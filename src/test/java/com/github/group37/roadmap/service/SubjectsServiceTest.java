package com.github.group37.roadmap.service;

import com.github.group37.roadmap.percistance.SubjectsRepo;
import com.github.group37.roadmap.percistance.models.SubjectsDao;
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
class SubjectsServiceTest {
    @Mock
    public SubjectsRepo subjectsRepo;

    @InjectMocks
    private SubjectsService subjectsService;

    private UUID validUuid = UUID.randomUUID();
    private List<SubjectsDao> subjectsDaos = List.of(subjectDao());

    private SubjectsDao subjectDao() {
        SubjectsDao subjectsDao = new SubjectsDao();
        subjectsDao.setId(validUuid);
        subjectsDao.setSubject("TESTNAME");
        return subjectsDao;
    }

    @DisplayName("findall subjects")
    @Test
    void when_given_valid_uuid_should_return_list_of_subjects() {
        given(subjectsRepo.findAll()).willReturn(subjectsDaos);
        List<SubjectsDao> serviceTest = subjectsService.getSubjects();
        assertThat(serviceTest.get(0).getId()).isEqualTo(subjectDao().getId());
        assertThat(serviceTest.get(0).getSubject()).isEqualTo(subjectDao().getSubject());
    }
}