package com.example.eshikshauser.Service.impl;

import com.example.eshikshauser.Entity.User;
import com.example.eshikshauser.Exception.EmailAlreadyExistsException;
import com.example.eshikshauser.Repository.UserRepository;
import com.example.eshikshauser.Service.UserService;
import com.example.eshikshauser.dto.UserDataDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepo;
    @Override
    public UserDataDto createUser(UserDataDto requestUser) {
        List<User> users=this.userRepo.findByEmail(requestUser.email);
        if(!users.isEmpty()){
            throw new EmailAlreadyExistsException("This email already exists");
        }
        this.userRepo.save(new User(requestUser.getName(), requestUser.getEmail(), requestUser.getGender(), requestUser.getPassword()));
        return requestUser;
    }
}
