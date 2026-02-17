package com.cloderno.card_management_system.controller;

import com.cloderno.card_management_system.dto.UserRequestDTO;
import com.cloderno.card_management_system.entity.User;
import com.cloderno.card_management_system.repository.UserRepository;
import com.sun.net.httpserver.Headers;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @GetMapping
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> create(
            @RequestBody UserRequestDTO userRequest
    ) {


        userRepository.save();

        Headers headers = new Headers();
        headers.add("Url", "headers");

        return ResponseEntity(HttpStatus.CREATED);
    }
}
