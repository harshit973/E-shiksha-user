package com.example.eshikshauser.Service.impl;

import com.example.eshikshauser.Entity.User;
import com.example.eshikshauser.Exception.RecordAlreadyExistsException;
import com.example.eshikshauser.Repository.UserRepository;
import com.example.eshikshauser.Service.UserService;
import com.example.eshikshauser.dto.UserDataDto;
import com.example.eshikshauser.dto.UserDataResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDataResponseDto createUser(UserDataDto requestUser) {
        List<User> users = this.userRepo.findByEmail(requestUser.email);
        if (!users.isEmpty()) {
            throw new RecordAlreadyExistsException("This email already exists");
        }
        String hash = this.encode(requestUser.getPassword());
        User user=this.userRepo.save(new User(requestUser.getName(), requestUser.getEmail(), requestUser.getGender(), hash));
        return new UserDataResponseDto(user.getId(), user.name, user.email, user.gender);
    }

    @Override
    public UserDataResponseDto getUser(Long id) {
        Optional<User> optionalUser =this.userRepo.findById(id);
        if(!optionalUser.isPresent()) return new UserDataResponseDto();
        User user=optionalUser.get();
        return new UserDataResponseDto(user.getId(), user.name,user.email,user.gender);
    }

    @Override
    public UserDataDto updateUser(Long id,UserDataDto requestUser) {
        String hash = this.encode(requestUser.getPassword());
        this.userRepo.updateUser(requestUser.getName(), requestUser.getEmail(), requestUser.getGender(), hash);
        requestUser.setId(id);
        return requestUser;
    }

    @Override
    public String encode(String password) {
        return this.passwordEncoder.encode(password);
    }

    @Override
    public Boolean authenticate(String password, String hash) {
        return this.passwordEncoder.matches(password, hash);
    }
}