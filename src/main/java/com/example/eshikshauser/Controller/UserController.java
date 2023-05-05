package com.example.eshikshauser.Controller;

import com.example.eshikshauser.Constants.ApiConstants;
import com.example.eshikshauser.Service.UserService;
import com.example.eshikshauser.dto.UserDataDto;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ApiConstants.version1+ApiConstants.seperator+ApiConstants.user,produces = MediaType.APPLICATION_JSON_VALUE)
@Log4j2
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping()
    public ResponseEntity<UserDataDto> create(@NonNull @RequestBody final UserDataDto requestUser){
        return new ResponseEntity<>(this.userService.createUser(requestUser), HttpStatus.CREATED);
    }
}
