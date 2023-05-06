package com.example.eshikshauser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducatorDataDto {
    public Long id;
    public UserDataResponseDto user;
    public Integer rating;
}
