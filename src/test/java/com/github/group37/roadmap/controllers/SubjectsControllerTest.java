package com.github.group37.roadmap.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.group37.roadmap.percistance.models.SubjectsDao;
import com.github.group37.roadmap.service.SubjectsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SubjectsController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
class SubjectsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubjectsService subjectsService;

    @Autowired
    private ObjectMapper objectMapper;


    private UUID validUuid = UUID.randomUUID();

    private List<SubjectsDao> subjectsDaoList = List.of(subjects());


    private SubjectsDao subjects(){
        SubjectsDao subjectsDao = new SubjectsDao();
        subjectsDao.setId(validUuid);
        subjectsDao.setSubject("SUBJECT");
        return subjectsDao;
    }
    @DisplayName("findall subjects")
    @Test
    void when_given_valid_uuid_return_list() throws Exception{
        when(subjectsService.getSubjects()).thenReturn(subjectsDaoList);

        mockMvc.perform(get("/subjects")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id").value(subjects().getId().toString()))
                .andExpect(jsonPath("$[0].subject").value(subjects().getSubject()));
    }


}