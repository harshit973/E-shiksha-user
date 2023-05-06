package com.example.eshikshauser.Service;

import com.example.eshikshauser.dto.UserDataDto;
import com.example.eshikshauser.dto.UserDataResponseDto;
import com.example.eshikshauser.dto.UserIdDto;

public interface UserService {
    UserDataResponseDto createUser(UserDataDto requestUser);
    UserDataResponseDto getUser(Long id);
    UserDataResponseDto getUserByEmail(String email);
    UserDataDto updateUser(Long id,UserDataDto requestUser);
    UserIdDto deleteUser(Long id);
    String encode(String password);
    Boolean authenticate(String password,String hash);
}
