package com.jpop.userservice.controller;


import com.jpop.userservice.model.UserTable;
import com.jpop.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/userService")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/Users")
    public ResponseEntity<List<UserTable>> getAllUsers(){

        List<UserTable> userList = userService.getAllUsers();
        if(userList.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userList,HttpStatus.OK);

    }

    @PostMapping("/Users")
    public ResponseEntity<UserTable> saveUser(@RequestBody UserTable UserTable){
        try{
            UserTable user =  userService.saveUser(UserTable);
            return new ResponseEntity<>(user,HttpStatus.CREATED);
        }
        catch(Exception ex){
            return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/Users/{UserId}")
    public ResponseEntity<Optional<UserTable>> getUserById(@PathVariable("UserId")int UserId){
        Optional<UserTable> userData =  userService.getUserById(UserId);

        if(userData.isPresent()){
            return new ResponseEntity<>(userData,HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("Users/{userId}")
    public ResponseEntity<UserTable> updateUserDetails(@PathVariable("userId") Integer userId,@RequestBody UserTable user){
        Optional<UserTable> userData = userService.getUserById(userId);
        if(userData.isPresent()){
            UserTable oldUserData = userData.get();
            oldUserData.setFirstName(user.getFirstName());
            oldUserData.setUserEmail(user.getUserEmail());
            return new ResponseEntity<>(userService.saveUser(oldUserData),HttpStatus.CREATED);

        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("Users/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable("id") int id){
        try{
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

}
