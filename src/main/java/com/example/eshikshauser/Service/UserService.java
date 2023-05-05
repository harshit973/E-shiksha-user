package com.example.eshikshauser.Service;

import com.example.eshikshauser.dto.UserDataDto;
import com.example.eshikshauser.dto.UserDataResponseDto;

public interface UserService {
    UserDataResponseDto createUser(UserDataDto requestUser);
    UserDataResponseDto getUser(Long id);
    UserDataDto updateUser(Long id,UserDataDto requestUser);
    String encode(String password);
    Boolean authenticate(String password,String hash);
}
