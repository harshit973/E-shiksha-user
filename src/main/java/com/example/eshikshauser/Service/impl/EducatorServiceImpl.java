package com.example.eshikshauser.Service.impl;

import com.example.eshikshauser.Entity.Educator;
import com.example.eshikshauser.Entity.User;
import com.example.eshikshauser.Exception.RecordNotExistsException;
import com.example.eshikshauser.Repository.EducatorRepository;
import com.example.eshikshauser.Repository.UserRepository;
import com.example.eshikshauser.Service.EducatorService;
import com.example.eshikshauser.dto.EducatorDataDto;
import com.example.eshikshauser.dto.UserDataResponseDto;
import com.example.eshikshauser.dto.UserIdDto;
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
        Optional<User> optionalUser=this.userRepo.findById(requestUser.getUser().getId());
        if(!optionalUser.isPresent()){
            throw new RecordNotExistsException("User is not available to be registered as educator");
        }
        Educator createdEducator=this.educatorRepo.save(new Educator(optionalUser.get(),requestUser.getRating()));
        return new UserIdDto(createdEducator.getId());
    }

    @Override
    public EducatorDataDto getEducator(Long id) {
        Optional<Educator> optionalEducator=this.educatorRepo.findById(id);
        if(!optionalEducator.isPresent() || optionalEducator.get().getDeleted() || optionalEducator.get().user.getDeleted()){
            throw new RecordNotExistsException("Educator does not exists");
        }
        Educator educator=optionalEducator.get();
        return new EducatorDataDto(educator.getId(),new UserDataResponseDto(educator.user.getId(),educator.user.name,educator.user.email,educator.user.gender),educator.rating);
    }

    @Override
    public EducatorDataDto getEducatorByEmail(String email) {
        List<Tuple> educatorStream=educatorRepo.findEducatorByUserEmail(email);
        final String errorMessage="Educator does not exists";
        if(!educatorStream.isEmpty()){
            Tuple educator=educatorStream.stream().findFirst().get();
            if((Boolean)educator.get("user_deleted") || (Boolean)educator.get("educator_deleted")){
                throw new RecordNotExistsException(errorMessage);
            }
            return new EducatorDataDto(((BigInteger)educator.get("id")).longValue(),new UserDataResponseDto(((BigInteger)educator.get("uid")).longValue(),(String)educator.get("name"),(String)educator.get("email"),(Integer)educator.get("gender")),(Integer)educator.get("rating"));
        }
        throw new RecordNotExistsException(errorMessage);
    }

    @Override
    public UserIdDto updateEducator(Long id, EducatorDataDto requestUser) {
        this.educatorRepo.updateRating(id, requestUser.getRating());
        return new UserIdDto(id);
    }

    @Override
    public UserIdDto deleteEducator(Long id) {
        this.educatorRepo.deleteEducator(id);
        return new UserIdDto(id);
    }
}