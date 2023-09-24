package com.example.user.Service;

import com.example.user.dto.EducatorDataDto;
import com.example.user.dto.UserIdDto;

public interface EducatorService {
    UserIdDto createEducator(EducatorDataDto requestUser);

    EducatorDataDto getEducator(Long id);

    EducatorDataDto getEducatorByEmail(String email);

    UserIdDto updateEducator(Long id, EducatorDataDto requestUser);

    UserIdDto deleteEducator(Long id);
}
