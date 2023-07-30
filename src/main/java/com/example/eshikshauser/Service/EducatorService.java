package com.example.eshikshauser.Service;

import com.example.eshikshauser.dto.EducatorDataDto;
import com.example.eshikshauser.dto.UserIdDto;

public interface EducatorService {
    UserIdDto createEducator(EducatorDataDto requestUser);

    EducatorDataDto getEducator(Long id);

    EducatorDataDto getEducatorByEmail(String email);

    UserIdDto updateEducator(Long id, EducatorDataDto requestUser);

    UserIdDto deleteEducator(Long id);
}
