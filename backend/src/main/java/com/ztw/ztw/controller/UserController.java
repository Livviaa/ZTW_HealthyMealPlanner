package com.ztw.ztw.controller;

import com.ztw.ztw.model.Message;
import com.ztw.ztw.model.User;
import com.ztw.ztw.service.UserService;
import com.ztw.ztw.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // SPECIFIC
    @GetMapping("/specific/users")
    public ResponseEntity<Object> getActualUser(Principal principal) {
        String email = Utils.getEmail(principal);
        User user = userService.getUserByEmail(email);
        String errorMessage;
        HttpStatus httpStatus;

        if (user != null) {
            httpStatus = HttpStatus.OK;
            return new ResponseEntity<>(user, httpStatus);
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + email + ".";
            return new ResponseEntity<>(new Message(errorMessage), httpStatus);
        }
    }

    @PutMapping("/specific/users")
    public ResponseEntity<Object> editActualUser(Principal principal, @RequestBody User user) {
        User editedUser = userService.editUser(user);
        String errorMessage;
        HttpStatus httpStatus;

        if (editedUser != null) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Updated: #" + editedUser.getEmail() + ".";
            return new ResponseEntity<>(errorMessage, httpStatus);
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + user.getEmail() + ".";
            return new ResponseEntity<>(new Message(errorMessage), httpStatus);
        }
    }

    // GENERAL
    // CREATE
    @PostMapping("/users")
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        User createdUser = userService.addUser(user);
        String errorMessage;
        HttpStatus httpStatus;

        if (createdUser != null) {
            httpStatus = HttpStatus.OK;
            errorMessage = "User: " + createdUser.getName() + " " + createdUser.getSurname() + " created.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: " + user + ".";
        }
        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }

    // READ
    @GetMapping("/users")
    public List<User> getUsers(Principal principal) {
        Map<String, Object> authDetails = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();

        String user = (String) authDetails.get("name");
        String email = (String) authDetails.get("email");

        List<User> users = userService.getUsers();

        return users.stream().filter(u -> u.getEmail().equals(email)).collect(Collectors.toList());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getSingleUser(@PathVariable Integer id) {
        User user = userService.getSingleUser(id);
        String errorMessage;
        HttpStatus httpStatus;

        if (user != null) {
            httpStatus = HttpStatus.OK;
            return new ResponseEntity<>(user, httpStatus);
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + id + ".";
            return new ResponseEntity<>(new Message(errorMessage), httpStatus);
        }
    }

    // UPDATE
    @PutMapping("/users")
    public ResponseEntity<Object> editUser(@RequestBody User user) {
        User editedUser = userService.editUser(user);
        String errorMessage;
        HttpStatus httpStatus;

        if (editedUser != null) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Updated: #" + editedUser.getUserId() + ".";
            return new ResponseEntity<>(errorMessage, httpStatus);
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + user.getUserId() + ".";
            return new ResponseEntity<>(new Message(errorMessage), httpStatus);
        }
    }

    // DELETE
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Integer id) {
        boolean result = userService.deleteUser(id);
        String errorMessage;
        HttpStatus httpStatus;

        if (result) {
            httpStatus = HttpStatus.OK;
            errorMessage = "User: #" + id + " deleted.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + id + ".";
        }
        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }
}
