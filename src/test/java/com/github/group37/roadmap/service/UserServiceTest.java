//package com.github.group37.roadmap.service;
//// https://www.javaguides.net/2022/03/spring-boot-unit-testing-service-layer.html#:~:text=In%20order%20to%20test%20Service,a%20database%20for%20Unit%20testing.&text=The%20Spring%20Boot%20Starter%20Test,testing%20the%20Spring%20Boot%20Applications.
//
//import com.github.group37.roadmap.other.UserRequest;
//import com.github.group37.roadmap.percistance.UserRepository;
//import com.github.group37.roadmap.percistance.models.UserDao;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.verify;
//
//@ExtendWith(MockitoExtension.class)
//public class UserServiceTest {
//
//    @Mock
//    private UserRepository userRepositiory;
//
//    @InjectMocks
//    private UserService userService;
//
//    private final UserDao user = new UserDao(UUID.randomUUID(), "chandler", "bing");
//    private final UserRequest userRequest = new UserRequest("chandler", "bing");
//
//    private final UserRequest updateUserRequest = new UserRequest("Joey", "Tribbiani");
//
//    @DisplayName("Should create and return a user succesfully")
//    @Test
//    public void givenUserObject_whenCreatingUser_returnsUser() {
//        given(userRepositiory.save(any())).willReturn(user);
//
//        UserDao savedUser = userService.create(userRequest);
//
//        assertThat(savedUser.getUuid()).isNotNull();
//        assertThat(savedUser.getUsername()).isEqualTo(userRequest.name());
//        assertThat(savedUser.getPassword()).isEqualTo(userRequest.password());
//    }
//
//    @DisplayName("should return a list of all users")
//    @Test
//    public void shouldReturnListOfAllUsers() {
//        given(userRepositiory.findAll()).willReturn(List.of(user));
//
//        List<UserDao> users = userService.readAll();
//
//        assertThat(users.get(0).getUuid()).isNotNull();
//        assertThat(users.get(0).getUsername()).isEqualTo(user.getUsername());
//        assertThat(users.get(0).getPassword()).isEqualTo(user.getPassword());
//    }
//
//    @DisplayName("should update user details")
//    @Test
//    public void whenGivenId_andUpdatedDetails_shouldReturnUserWithUpdatedDetails() {
//        given(userRepositiory.findByUUID(user.getUuid()))
//                .willReturn(
//                        Optional.of(new UserDao(user.getUuid(), updateUserRequest.name(), updateUserRequest.password())));
//
//        Optional<UserDao> users = userService.update(user.getUuid(), updateUserRequest.name(), updateUserRequest.password());
//        if (users.isPresent()) {
//            assertThat(users.get().getUuid()).isNotNull();
//            assertThat(users.get().getUsername()).isEqualTo(user.getUsername());
//            assertThat(users.get().getPassword()).isEqualTo(user.getPassword());
//        }
//    }
//
//    @DisplayName("shouldnt update user details")
//    @Test
//    public void whenInGivenId_andUpdatedDetails_shouldReturnEmptyOptional() {
//        given(userRepositiory.findByUUID(user.getUuid())).willReturn(Optional.empty());
//        Optional<UserDao> users = userService.update(user.getUuid(), updateUserRequest.name(), updateUserRequest.password());
//        assertThat(users.isPresent()).isFalse();
//    }
//
//    @DisplayName("should return user when given ID")
//    @Test
//    public void whenGivenId_returnUser() {
//        given(userRepositiory.findByUUID(user.getUuid())).willReturn(Optional.of(user));
//        Optional<UserDao> findUser = userService.findById(user.getUuid());
//
//        assertThat(findUser.get().getUuid()).isNotNull();
//        assertThat(findUser.get().getUsername()).isEqualTo(user.getUsername());
//        assertThat(findUser.get().getPassword()).isEqualTo(user.getPassword());
//    }
//
//    @DisplayName("should not return user when given ID")
//    @Test
//    public void whenInvalidGivenId_returnEmptyOptional() {
//        given(userRepositiory.findByUUID(user.getUuid())).willReturn(Optional.empty());
//        Optional<UserDao> findUser = userService.findById(user.getUuid());
//        assertThat(findUser.isPresent()).isFalse();
//    }
//
//}
