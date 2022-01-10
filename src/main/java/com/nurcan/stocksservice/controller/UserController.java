package com.nurcan.stocksservice.controller;

import com.nurcan.stocksservice.dto.UserDto;
import com.nurcan.stocksservice.service.AuthenticationFacadeService;
import com.nurcan.stocksservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";

    private UserService userService;
    private AuthenticationFacadeService authenticationFacadeService;

    @Secured({ROLE_ADMIN})
    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        log.info(String.format("received request to list user %s", authenticationFacadeService.getAuthentication().getPrincipal()));
        return new ResponseEntity<List<UserDto>>(userService.findAll(), HttpStatus.CREATED);
    }

    @Secured({ROLE_ADMIN})
    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto user) {
        log.info(String.format("received request to create user %s", authenticationFacadeService.getAuthentication().getPrincipal()));
        return new ResponseEntity<UserDto>(userService.save(user), HttpStatus.CREATED);
    }

    @Secured({ROLE_ADMIN, ROLE_USER})
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable long id) {
        log.info(String.format("received request to update user %s", authenticationFacadeService.getAuthentication().getPrincipal()));
        return new ResponseEntity<UserDto>(userService.findById(id), HttpStatus.OK);
    }

    @Secured({ROLE_ADMIN})
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        log.info(String.format("received request to delete user %s", authenticationFacadeService.getAuthentication().getPrincipal()));
        userService.delete(id);
    }


}