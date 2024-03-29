package com.example.user.Service.impl;

import com.example.user.Constants.ErrorMessages;
import com.example.user.Entity.User;
import com.example.user.Exception.RecordAlreadyExistsException;
import com.example.user.Exception.RecordNotExistsException;
import com.example.user.Repository.UserRepository;
import com.example.user.Service.UserService;
import com.example.user.dto.UserDataDto;
import com.example.user.dto.UserDataResponseDto;
import com.example.user.dto.UserIdDto;
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
    public UserIdDto createUser(UserDataDto requestUser) {
        List<User> users = this.userRepo.findByEmail(requestUser.email);
        if (!users.isEmpty()) {
            throw new RecordAlreadyExistsException(ErrorMessages.EMAIL_ALREADY_EXISTS);
        }
        String hash = this.encode(requestUser.getPassword());
        User user = this.userRepo.save(new User(requestUser.getName(), requestUser.getEmail(), requestUser.getGender(), hash));
        return new UserIdDto(user.getId());
    }

    @Override
    public UserDataResponseDto getUser(Long id) {
        Optional<User> optionalUser = this.userRepo.findById(id);
        if (!optionalUser.isPresent() || optionalUser.get().getDeleted())
            throw new RecordNotExistsException(ErrorMessages.USER_NOT_EXISTS);
        User user = optionalUser.get();
        return new UserDataResponseDto(user.getId(), user.getName(), user.getEmail(), user.getGender());
    }

    @Override
    public UserDataResponseDto getUserByEmail(String email) {
        List<User> users = this.userRepo.findByEmail(email);
        if (users.isEmpty()) throw new RecordNotExistsException(ErrorMessages.USER_NOT_EXISTS);
        User user = users.stream().findFirst().get();
        if (user.getDeleted()) throw new RecordNotExistsException(ErrorMessages.USER_NOT_EXISTS);
        return new UserDataResponseDto(user.getId(), user.getName(), user.getEmail(), user.getGender());
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