//package com.github.group37.roadmap.controllers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.github.group37.roadmap.other.UserRequest;
//import com.github.group37.roadmap.percistance.models.UserDao;
//import com.github.group37.roadmap.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.transaction.TransactionSystemException;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(controllers = UserController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
//class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService service;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private final UserDao user1 = new UserDao(UUID.randomUUID(), "abdi", "smith");
//    private final UserRequest userRequest1 = new UserRequest("abdi", "smith");
//    private final UserRequest userRequestValidUnameAndPw = new UserRequest("changed", "changed");
//    private final UserRequest longUserRequest = new UserRequest(
//            "aaaaaaaaaaaaaaaaaaaaLongerThan36Charsaaaaaaaaaaaaaaaaaaaa",
//            "aaaaaaaaaaaaaaaaaaaaLongerThan36Charsaaaaaaaaaaaaaaaaaaaa");
//    private final UserRequest shortUserRequest = new UserRequest("he", "he");
//
//    @Test
//    void ShouldGetListOfUsersSuccessfully() throws Exception {
//        when(service.readAll()).thenReturn(List.of(user1));
//        this.mockMvc
//                .perform(get("/users"))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(content().contentType("application/json"))
//                .andExpect(jsonPath("$[0].uuid").value(user1.getUuid().toString()))
//                .andExpect(jsonPath("$[0].username").value(user1.getUsername()))
//                .andExpect(jsonPath("$[0].password").value(user1.getPassword()));
//    }
//
//    @Test
//    void ShouldCreateAndReturnUserSuccsesfully() throws Exception {
//        when(service.create(userRequest1)).thenReturn(user1);
//        this.mockMvc
//                .perform(post("/users")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(userRequest1))
//                        .characterEncoding("utf-8"))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.username").value(user1.getUsername()))
//                .andExpect(jsonPath("$.password").value(user1.getPassword()));
//    }
//
//    @Test
//    void WhenGivenIdAndUserIsFound() throws Exception {
//        when(service.findById(user1.getUuid())).thenReturn(Optional.of(user1));
//        this.mockMvc
//                .perform(get("/users/" + user1.getUuid()).accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.uuid").value(user1.getUuid().toString()))
//                .andExpect(jsonPath("$.username").value(user1.getUsername()))
//                .andExpect(jsonPath("$.password").value(user1.getPassword()));
//    }
//
//    @Test
//    void WhenGivenIdAndUserIsNotFound() throws Exception {
//        when(service.findById(user1.getUuid())).thenReturn(Optional.empty());
//        this.mockMvc
//                .perform(get("/users/" + user1.getUuid()).accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void shouldUpdateUser() throws Exception {
//        UserDao updatedUser = new UserDao(user1.getUuid(), userRequestValidUnameAndPw.name(), userRequestValidUnameAndPw.password());
//        when(service.update(user1.getUuid(), userRequestValidUnameAndPw.name(), userRequestValidUnameAndPw.password()))
//                .thenReturn(Optional.of(updatedUser));
//        this.mockMvc
//                .perform(put("/users/" + updatedUser.getUuid())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(userRequestValidUnameAndPw))
//                        .characterEncoding("utf-8"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.uuid").value(updatedUser.getUuid().toString()))
//                .andExpect(jsonPath("$.username").value(updatedUser.getUsername()))
//                .andExpect(jsonPath("$.password").value(updatedUser.getPassword()));
//    }
//
//    @Test
//    void shouldNotUpdateNonExistingUser() throws Exception {
//        when(service.update(user1.getUuid(), userRequestValidUnameAndPw.name(), userRequestValidUnameAndPw.password()))
//                .thenReturn(Optional.empty());
//        this.mockMvc
//                .perform(put("/users/" + user1.getUuid())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(user1))
//                        .characterEncoding("utf-8"))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void shouldNotUpdateUserWhenChosenNameAndPasswordIsTooLong() throws Exception {
//        UserDao updatedUser = new UserDao(user1.getUuid(), longUserRequest.name(), longUserRequest.password());
//        when(service.update(user1.getUuid(), longUserRequest.name(), longUserRequest.password()))
//                .thenThrow(new TransactionSystemException("500"));
//        this.mockMvc
//                .perform(put("/users/" + updatedUser.getUuid())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(longUserRequest))
//                        .characterEncoding("utf-8"))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void shouldNotUpdateUserWhenNameAndPasswordIsTooShort() throws Exception {
//        UserDao updatedUser = new UserDao(user1.getUuid(), shortUserRequest.name(), shortUserRequest.password());
//        when(service.update(user1.getUuid(), shortUserRequest.name(), shortUserRequest.password()))
//                .thenThrow(new TransactionSystemException("500"));
//        this.mockMvc
//                .perform(put("/users/" + updatedUser.getUuid())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(shortUserRequest))
//                        .characterEncoding("utf-8"))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void givenUserId_whenDeleteCalled_returnNothing() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", user1.getUuid()))
//                .andExpect(status().isOk());
//    }
//
//}
