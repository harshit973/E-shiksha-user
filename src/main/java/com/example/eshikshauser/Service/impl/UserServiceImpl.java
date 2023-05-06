package com.example.eshikshauser.Service.impl;

import com.example.eshikshauser.Entity.User;
import com.example.eshikshauser.Exception.RecordAlreadyExistsException;
import com.example.eshikshauser.Exception.RecordNotExistsException;
import com.example.eshikshauser.Repository.UserRepository;
import com.example.eshikshauser.Service.UserService;
import com.example.eshikshauser.dto.UserDataDto;
import com.example.eshikshauser.dto.UserDataResponseDto;
import com.example.eshikshauser.dto.UserIdDto;
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
        User user = this.userRepo.save(new User(requestUser.getName(), requestUser.getEmail(), requestUser.getGender(), hash));
        return new UserDataResponseDto(user.getId(), user.name, user.email, user.gender);
    }

    @Override
    public UserDataResponseDto getUser(Long id) {
        Optional<User> optionalUser = this.userRepo.findById(id);
        if (!optionalUser.isPresent() || optionalUser.get().getDeleted()) throw new RecordNotExistsException("User does not exists");
        User user = optionalUser.get();
        return new UserDataResponseDto(user.getId(), user.name, user.email, user.gender);
    }

    @Override
    public UserDataResponseDto getUserByEmail(String email) {
        List<User> users = this.userRepo.findByEmail(email);
        if (users.isEmpty()) throw new RecordNotExistsException("User does not exists");
        User user = users.stream().findFirst().get();
        if(user.getDeleted()) throw new RecordNotExistsException("User does not exists");
        return new UserDataResponseDto(user.getId(), user.name, user.email, user.gender);
    }

    @Override
    public UserDataDto updateUser(Long id, UserDataDto requestUser) {
        String hash = this.encode(requestUser.getPassword());
        this.userRepo.updateUser(id, requestUser.getName(), requestUser.getEmail(), requestUser.getGender(), hash);
        requestUser.setId(id);
        return requestUser;
    }

    @Override
    public UserIdDto deleteUser(Long id) {
        this.userRepo.deleteUser(id);
        return new UserIdDto(id);
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