package com.example.eshikshauser.Controller;

import com.example.eshikshauser.Constants.ApiConstants;
import com.example.eshikshauser.Exception.ControllerException;
import com.example.eshikshauser.Exception.RecordAlreadyExistsException;
import com.example.eshikshauser.Service.UserService;
import com.example.eshikshauser.dto.UserDataDto;
import com.example.eshikshauser.dto.UserDataResponseDto;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = ApiConstants.version1 + ApiConstants.seperator + ApiConstants.user, produces = MediaType.APPLICATION_JSON_VALUE)
@Log4j2
public class UserController {
    @Autowired(required = true)
    UserService userService;

    @PostMapping()
    public ResponseEntity<?> create(@NonNull @RequestBody final UserDataDto requestUser) {
        try {
            return new ResponseEntity<>(this.userService.createUser(requestUser), HttpStatus.CREATED);
        } catch (RecordAlreadyExistsException exception) {
            ControllerException ce = new ControllerException(exception.getMessage(), exception.getStatus());
            return new ResponseEntity<>(ce, ce.getStatus());
        }
    }

    @PutMapping(ApiConstants.seperator + "{id}")
    public ResponseEntity<UserDataDto> update(@NonNull @PathVariable("id") final Long id, @NonNull @RequestBody final UserDataDto requestUser) {
        return new ResponseEntity<>(this.userService.updateUser(id, requestUser), HttpStatus.ACCEPTED);
    }

    @GetMapping(ApiConstants.seperator + "{id}")
    public ResponseEntity<UserDataResponseDto> get(@NonNull @PathVariable("id") final Long id) {
        return new ResponseEntity<>(this.userService.getUser(id), HttpStatus.ACCEPTED);
    }

}
