package com.example.howtoquery.controller;

import com.example.howtoquery.model.User;
import com.example.howtoquery.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/v1/users")
public class UserControllerV1 {

    private final UserService userService;

    public UserControllerV1(@Qualifier(value = "userServiceV1") UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<User>> getUsers(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "age", required = false) Integer age,
            @RequestParam(value = "createdFrom", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date createdFrom,
            @RequestParam(value = "createdTo", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date createdTo,
            @PageableDefault(value=3) Pageable pagination
    ) {
        return ResponseEntity.ok(userService.searchUsers(
                name,
                age,
                createdFrom,
                createdTo,
                pagination));
    }
}
