package com.example.eshikshauser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDataDto {
    public String name;
    public String email;
    public Integer gender;
    public String password;
}
