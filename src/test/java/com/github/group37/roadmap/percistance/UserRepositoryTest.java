//package com.github.group37.roadmap.percistance;
//
//import com.github.group37.roadmap.percistance.models.UserDao;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@Disabled
//@DataJpaTest
//public class UserRepositoryTest {
//    private final UserDao user = new UserDao(UUID.fromString("6598bb36-1de4-4e06-99fa-555c80f12781"), "user1", "user1");
//    @Autowired
//    private TestEntityManager entityManager;
//    @Autowired
//    private UserRepository userRepository;
//
//    @DisplayName("should get all users from the database")
//    @Test
//    public void whenCalled_returnListOfUsers() {
//        List<UserDao> testList = List.of(user);
//        List<UserDao> foundUsers = userRepository.findAll();
//        assertEquals(testList.size(), foundUsers.size());
//    }
//
//    @DisplayName("Should save to database")
//    @Test
//    public void whenGivenUser_shouldSave() {
//        UserDao user = new UserDao(UUID.randomUUID(), "Ross", "Geller");
//        userRepository.save(user);
//        UserDao isUserFound = userRepository.findByUUID(user.getUuid()).get();
//        assertEquals(user.getUuid(), isUserFound.getUuid());
//    }
//
//    @DisplayName("should get user by ID")
//    @Test
//    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {
//
//        UserDao getUser = userRepository.findById(user.getUuid()).get();
//
//        assertEquals(getUser.getUuid(), UUID.fromString("6598bb36-1de4-4e06-99fa-555c80f12781"));
//        assertEquals(getUser.getUsername(), "user1");
//        assertEquals(getUser.getPassword(), "user1");
//    }
//
//    @DisplayName("testing update")
//    @Test
//    public void givenEmployeeUpdateDetails_whenUpdateCalled_shouldUpdateChosenUser() {
//        UserDao createdUser = new UserDao(UUID.randomUUID(), "Pheobe", "Buffay");
//        userRepository.save(createdUser);
//
//        // update like this:
//        Optional<UserDao> userToUpdateOptional = userRepository.findById(createdUser.getUuid());
//        UserDao userToUpdate = userToUpdateOptional.get();
//
//        userToUpdate.setUsername("David");
//        userToUpdate.setPassword("Goggins");
//        userRepository.save(userToUpdate);
//
//        // update complete, now check if update worked properly
//
//        Optional<UserDao> checkUser = userRepository.findById(createdUser.getUuid());
//        assertEquals(checkUser.get().getUsername(), "David");
//        assertEquals(checkUser.get().getPassword(), "Goggins");
//    }
//
//    @DisplayName("Should delete usere")
//    @Test
//    public void whenGivenIdOfUserToDelete_shouldDelete_checkDbIfStillThere() {
//        UserDao userToDelete = new UserDao(UUID.randomUUID(), "Mo", "Farah");
//        userRepository.save(userToDelete);
//        userRepository.deleteById(userToDelete.getUuid());
//
//        Optional<UserDao> getUser = userRepository.findById(userToDelete.getUuid());
//        Optional<UserDao> emptyOptional = Optional.empty();
//        assertEquals(getUser, emptyOptional);
//    }
//}
