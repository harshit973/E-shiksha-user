package com.example.user.Service.impl;

import com.example.user.Constants.ErrorMessages;
import com.example.user.Entity.Educator;
import com.example.user.Entity.User;
import com.example.user.Exception.RecordNotExistsException;
import com.example.user.Repository.EducatorRepository;
import com.example.user.Repository.UserRepository;
import com.example.user.Service.EducatorService;
import com.example.user.dto.EducatorDataDto;
import com.example.user.dto.UserDataResponseDto;
import com.example.user.dto.UserIdDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class EducatorServiceImpl implements EducatorService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private EducatorRepository educatorRepo;


    @Override
    public UserIdDto createEducator(EducatorDataDto requestUser) {
        Optional<User> optionalUser = this.userRepo.findById(requestUser.getUser().getId());
        if (!optionalUser.isPresent()) {
            throw new RecordNotExistsException(ErrorMessages.USER_NOT_EXISTS);
        }
        Educator createdEducator = this.educatorRepo.save(new Educator(optionalUser.get(), requestUser.getExperience(), requestUser.getRating()));
        return new UserIdDto(createdEducator.getId());
    }

    @Override
    public EducatorDataDto getEducator(Long id) {
        Optional<Educator> optionalEducator = this.educatorRepo.findById(id);
        if (!optionalEducator.isPresent() || optionalEducator.get().getDeleted() || optionalEducator.get().getUser().getDeleted()) {
            throw new RecordNotExistsException(ErrorMessages.EDUCATOR_NOT_EXISTS);
        }
        Educator educator = optionalEducator.get();
        return new EducatorDataDto(educator.getId(), new UserDataResponseDto(educator.getUser().getId(), educator.getUser().getName(), educator.getUser().getEmail(), educator.getUser().getGender()), educator.getExperience(), educator.getRating());
    }

    @Override
    public EducatorDataDto getEducatorByEmail(String email) {
        List<Tuple> educatorStream = educatorRepo.findEducatorByUserEmail(email);
        if (!educatorStream.isEmpty()) {
            Tuple educator = educatorStream.stream().findFirst().get();
            if ((Boolean) educator.get("user_deleted") || (Boolean) educator.get("educator_deleted")) {
                throw new RecordNotExistsException(ErrorMessages.EDUCATOR_NOT_EXISTS);
            }
            return new EducatorDataDto(((BigInteger) educator.get("id")).longValue(), new UserDataResponseDto(((BigInteger) educator.get("uid")).longValue(), (String) educator.get("name"), (String) educator.get("email"), (Integer) educator.get("gender")), (Integer) educator.get("rating"), (Integer) educator.get("rating"));
        }
        throw new RecordNotExistsException(ErrorMessages.EDUCATOR_NOT_EXISTS);
    }

    @Override
    public UserIdDto updateEducator(Long id, EducatorDataDto requestUser) {
        this.educatorRepo.updateRating(id, requestUser.getRating(), requestUser.getExperience());
        return new UserIdDto(id);
    }

    @Override
    public UserIdDto deleteEducator(Long id) {
        this.educatorRepo.deleteEducator(id);
        return new UserIdDto(id);
    }
}