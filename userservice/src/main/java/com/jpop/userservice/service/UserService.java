package com.jpop.userservice.service;


import com.jpop.userservice.dao.UserRepository;
import com.jpop.userservice.model.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository UserRepository;

    public List<UserTable> getAllUsers(){
        List<UserTable> UsersList = new ArrayList<>();
        UserRepository.findAll().forEach(Users -> UsersList.add(Users));
        return UsersList;
    }

    public UserTable saveUser(UserTable Users){
        return UserRepository.save(Users);
    }

    public void deleteUser(int id){
        UserRepository.deleteById(id);
    }

    public Optional<UserTable> getUserById(int id){
        return UserRepository.findById(id);

    }
}
