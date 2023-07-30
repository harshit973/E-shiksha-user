package com.example.eshikshauser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDataResponseDto {

    public Long id;

    public String name;

    public String email;

    public Integer gender;

}
