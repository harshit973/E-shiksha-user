package com.example.user.Controller;

import com.example.user.Constants.ApiConstants;
import com.example.user.Exception.ControllerException;
import com.example.user.Exception.RecordNotExistsException;
import com.example.user.Service.EducatorService;
import com.example.user.dto.EducatorDataDto;
import com.example.user.dto.UserIdDto;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = ApiConstants.version1 + ApiConstants.separator + ApiConstants.educator, produces = MediaType.APPLICATION_JSON_VALUE)
@Log4j2
public class EducatorController {
    @Autowired
    EducatorService educatorService;

    @PostMapping()
    public ResponseEntity<?> create(@NonNull @RequestBody final EducatorDataDto requestUser) {
        try {
            return new ResponseEntity<>(this.educatorService.createEducator(requestUser), HttpStatus.CREATED);
        } catch (RecordNotExistsException exception) {
            ControllerException ce = new ControllerException(exception.getMessage(), exception.getStatus());
            return new ResponseEntity<>(ce, ce.getStatus());
        }
    }

    @PutMapping(ApiConstants.separator + "{id}")
    public ResponseEntity<UserIdDto> update(@NonNull @PathVariable(value = "id") final Long id, @NonNull @RequestBody final EducatorDataDto requestUser) {
        return new ResponseEntity<>(this.educatorService.updateEducator(id, requestUser), HttpStatus.OK);
    }

    @GetMapping(ApiConstants.separator + "id" + ApiConstants.separator + "{id}")
    public ResponseEntity<?> getById(@NonNull @PathVariable(value = "id") final Long id) {
        try {
            return new ResponseEntity<>(this.educatorService.getEducator(id), HttpStatus.OK);
        } catch (RecordNotExistsException exception) {
            ControllerException ce = new ControllerException(exception.getMessage(), exception.getStatus());
            return new ResponseEntity<>(ce, ce.getStatus());
        }
    }

    @GetMapping(ApiConstants.separator + "email" + ApiConstants.separator + "{email}")
    public ResponseEntity<?> getByEmail(@NonNull @PathVariable(value = "email") final String email) {
        try {
            return new ResponseEntity<>(this.educatorService.getEducatorByEmail(email), HttpStatus.OK);
        } catch (RecordNotExistsException exception) {
            ControllerException ce = new ControllerException(exception.getMessage(), exception.getStatus());
            return new ResponseEntity<>(ce, ce.getStatus());
        }
    }

    @DeleteMapping(ApiConstants.separator + "{id}")
    public ResponseEntity<UserIdDto> delete(@NonNull @PathVariable(value = "id") final Long id) {
        return new ResponseEntity<>(this.educatorService.deleteEducator(id), HttpStatus.OK);
    }


}
