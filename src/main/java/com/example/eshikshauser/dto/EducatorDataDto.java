package com.example.eshikshauser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducatorDataDto {

    private Long id;

    private UserDataResponseDto user;

    private Integer experience;

    private Integer rating;

}
