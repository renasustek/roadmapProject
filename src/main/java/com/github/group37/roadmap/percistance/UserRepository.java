//package com.github.group37.roadmap.percistance;
//
//import com.github.group37.roadmap.percistance.models.UserDao;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//import java.util.UUID;
//
//@Repository
//public interface UserRepository extends JpaRepository<UserDao, UUID> {
//
//    @Query("SELECT u FROM UserDao u WHERE u.uuid = ?1")
//    Optional<UserDao> findByUUID(UUID uuid);
//
//    @Query("DELETE FROM UserDao u WHERE u.uuid = ?1")
//    void deleteByUUID(UUID uuid);
//}
