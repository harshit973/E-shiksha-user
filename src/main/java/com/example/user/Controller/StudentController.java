package com.example.user.Controller;

import com.example.user.Constants.ApiConstants;
import com.example.user.Exception.ControllerException;
import com.example.user.Exception.InvalidCredentialsException;
import com.example.user.Exception.RecordAlreadyExistsException;
import com.example.user.Exception.RecordNotExistsException;
import com.example.user.Service.UserService;
import com.example.user.dto.LoginResponseDto;
import com.example.user.dto.UserDataDto;
import com.example.user.dto.UserIdDto;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = ApiConstants.version1 + ApiConstants.separator + ApiConstants.user, produces = MediaType.APPLICATION_JSON_VALUE)
@Log4j2
public class StudentController {
    @Autowired
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

    @PostMapping(ApiConstants.separator + "authenticate")
    public ResponseEntity<?> authenticate(@NonNull @RequestBody final UserDataDto requestUser) {
        try {
            return new ResponseEntity<>(this.userService.authenticate(requestUser.getEmail(),requestUser.getPassword()), HttpStatus.CREATED);
        } catch (InvalidCredentialsException exception) {
            ControllerException ce = new ControllerException(exception.getMessage(), exception.getStatus());
            return new ResponseEntity<>(ce, ce.getStatus());
        }
    }

    @PostMapping(ApiConstants.separator + "login")
    public ResponseEntity<?> login(@NonNull @RequestBody final UserDataDto requestUser) {
        try{
            return new ResponseEntity<>(this.userService.login(requestUser), HttpStatus.CREATED);
        } catch (InvalidCredentialsException exception) {
            ControllerException ce = new ControllerException(exception.getMessage(), exception.getStatus());
            return new ResponseEntity<>(ce, ce.getStatus());
        }

    }

    @PutMapping(ApiConstants.separator + "{id}")
    public ResponseEntity<UserDataDto> update(@NonNull @PathVariable("id") final Long id, @NonNull @RequestBody final UserDataDto requestUser) {
        return new ResponseEntity<>(this.userService.updateUser(id, requestUser), HttpStatus.OK);
    }

    @GetMapping(ApiConstants.separator + "id/{id}")
    public ResponseEntity<?> get(@NonNull @PathVariable("id") final Long id) {
        try {
            return new ResponseEntity<>(this.userService.getUser(id), HttpStatus.OK);
        } catch (RecordNotExistsException exception) {
            ControllerException ce = new ControllerException(exception.getMessage(), exception.getStatus());
            return new ResponseEntity<>(ce, ce.getStatus());
        }
    }

    @GetMapping(ApiConstants.separator + "email/{email}")
    public ResponseEntity<?> getByEmail(@NonNull @PathVariable("email") final String email) {
        try {
            return new ResponseEntity<>(this.userService.getUserByEmail(email), HttpStatus.OK);
        } catch (RecordNotExistsException exception) {
            ControllerException ce = new ControllerException(exception.getMessage(), exception.getStatus());
            return new ResponseEntity<>(ce, ce.getStatus());
        }
    }

    @DeleteMapping(ApiConstants.separator + "{id}")
    public ResponseEntity<UserIdDto> delete(@NonNull @PathVariable("id") final Long id) {
        return new ResponseEntity<>(this.userService.deleteUser(id), HttpStatus.OK);
    }

}