package com.example.eshikshauser.Controller;

import com.example.eshikshauser.Constants.ApiConstants;
import com.example.eshikshauser.Exception.ControllerException;
import com.example.eshikshauser.Exception.RecordAlreadyExistsException;
import com.example.eshikshauser.Exception.RecordNotExistsException;
import com.example.eshikshauser.Service.EducatorService;
import com.example.eshikshauser.dto.EducatorDataDto;
import com.example.eshikshauser.dto.UserDataDto;
import com.example.eshikshauser.dto.UserIdDto;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = ApiConstants.version1 + ApiConstants.seperator + ApiConstants.educator, produces = MediaType.APPLICATION_JSON_VALUE)
@Log4j2
public class EducatorController {
    @Autowired
    EducatorService educatorService;

    @PostMapping()
    public ResponseEntity<?> create(@NonNull @RequestBody final EducatorDataDto requestUser) {
        try{
            return new ResponseEntity<>(this.educatorService.createEducator(requestUser), HttpStatus.CREATED);
        }catch (RecordNotExistsException exception){
            ControllerException ce=new ControllerException(exception.getMessage(), exception.getStatus());
            return new ResponseEntity<>(ce,ce.getStatus());
        }
    }
    @PutMapping(ApiConstants.seperator+"{id}")
    public ResponseEntity<UserIdDto> update(@NonNull @PathVariable(value = "id") final Long id  ,@NonNull @RequestBody final EducatorDataDto requestUser) {
        return new ResponseEntity<>(this.educatorService.updateEducator(id,requestUser), HttpStatus.OK);
    }

    @GetMapping(ApiConstants.seperator+"id"+ApiConstants.seperator+"{id}")
    public ResponseEntity<?> getById(@NonNull @PathVariable(value = "id") final Long id) {
        try{
            return new ResponseEntity<>(this.educatorService.getEducator(id), HttpStatus.OK);
        }catch (RecordNotExistsException exception){
            ControllerException ce=new ControllerException(exception.getMessage(), exception.getStatus());
            return new ResponseEntity<>(ce,ce.getStatus());
        }
    }
    @GetMapping(ApiConstants.seperator+"email"+ApiConstants.seperator+"{email}")
    public ResponseEntity<?> getByEmail(@NonNull @PathVariable(value = "email") final String email) {
        try{
            return new ResponseEntity<>(this.educatorService.getEducatorByEmail(email), HttpStatus.OK);
        }catch (RecordNotExistsException exception){
            ControllerException ce=new ControllerException(exception.getMessage(), exception.getStatus());
            return new ResponseEntity<>(ce,ce.getStatus());
        }
    }

    @DeleteMapping(ApiConstants.seperator+"{id}")
    public ResponseEntity<UserIdDto> delete(@NonNull @PathVariable(value = "id") final Long id) {
        return new ResponseEntity<>(this.educatorService.deleteEducator(id), HttpStatus.OK);
    }


}
