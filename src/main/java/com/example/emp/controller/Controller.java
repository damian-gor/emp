package com.example.emp.controller;

import com.example.emp.dto.UserDTO;
import com.example.emp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{login}")
    public ResponseEntity<UserDTO> getUserDTOByLogin(@PathVariable("login") String login) {
        return ResponseEntity.ok(userService.getUserDTOByLogin(login));
    }

}
