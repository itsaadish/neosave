package com.example.neosave.controller;


import com.example.neosave.dto.UserDTO;
import com.example.neosave.exceptions.DuplicateEmailFound;
import com.example.neosave.exceptions.InvalidEmail;
import com.example.neosave.exceptions.PinCodeNotFound;
import com.example.neosave.exceptions.UserNotFound;
import com.example.neosave.models.User;
import com.example.neosave.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {


    private UserService userService;

    @Autowired
    public UserController(UserService mUserService) {
        this.userService = mUserService;
    }

    @PostMapping("/create/user")
    public ResponseEntity<User> addNewUser(@RequestBody UserDTO userDTO) throws PinCodeNotFound, DuplicateEmailFound, InvalidEmail {
        System.out.println(userDTO);
        User addedUser = userService.addNewUser(userDTO);
        return ResponseEntity.status(HttpStatus.OK).body(addedUser);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId) throws UserNotFound {
        User addedUser = userService.getNewUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(addedUser);
    }


}
