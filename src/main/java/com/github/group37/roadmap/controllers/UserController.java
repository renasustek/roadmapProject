package com.github.group37.roadmap.controllers;

import com.github.group37.roadmap.errors.UserNotFoundException;
import com.github.group37.roadmap.other.UserRequest;
import com.github.group37.roadmap.percistance.models.UserDao;
import com.github.group37.roadmap.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(value = "/register",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody UserRequest userRequest) {
        userService.create(userRequest);
        return ResponseEntity.ok("User created successfully");
    }


//    @DeleteMapping("/{username}")
//    public ResponseEntity<?> deleteUser(@PathVariable String username) {
//        userService.create(username);
//        return ResponseEntity.ok("User deleted successfully");
//    }
//
//    @PutMapping("/updateUsername")
//    public ResponseEntity<?> updateUsername(@RequestParam String oldUsername, @RequestParam String newUsername) {
//        userService.updateUsername(oldUsername, newUsername);
//        return ResponseEntity.ok("Username updated successfully");
//    }
//
//    @PutMapping("/updatePassword")
//    public ResponseEntity<?> updatePassword(@RequestParam String username, @RequestParam String newPassword) {
//        userService.updatePassword(username, newPassword);
//        return ResponseEntity.ok("Password updated successfully");
//    }
//    @GetMapping("/{username}")
//    public UserDao get(@PathVariable UUID id) {
//        return userService.findById(id).orElseThrow(() -> new UserNotFoundException(id));
//    } // TODO test delete


}