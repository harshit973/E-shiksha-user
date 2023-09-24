package com.example.user.Service;

import com.example.user.Entity.User;
import com.example.user.dto.LoginResponseDto;
import com.example.user.dto.UserDataDto;
import com.example.user.dto.UserDataResponseDto;
import com.example.user.dto.UserIdDto;

public interface UserService {
    UserIdDto createUser(UserDataDto requestUser);

    UserDataResponseDto getUser(Long id);

    UserDataResponseDto getUserByEmail(String email);
    LoginResponseDto login(UserDataDto userDataDto);

    UserDataDto updateUser(Long id, UserDataDto requestUser);

    UserIdDto deleteUser(Long id);

    String encode(String password);

    User authenticate(String email, String password);
}
