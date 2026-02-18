package com.cloderno.card_management_system.controller;

import com.cloderno.card_management_system.dto.UserRequestDTO;
import com.cloderno.card_management_system.dto.UserResponseDTO;
import com.cloderno.card_management_system.entity.User;
import com.cloderno.card_management_system.repository.UserRepository;
import com.cloderno.card_management_system.service.UserService;
import com.cloderno.card_management_system.util.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    private static final String BASE_URL = "/api/users";

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        List<User> users = userService.findAll();
        List<UserResponseDTO> userResponseDTOS = users.stream()
                .map(userMapper::toResponseDTO)
                .toList();

        return ResponseEntity.ok(userResponseDTOS);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(
        @Valid @RequestBody UserRequestDTO userRequest,
        UriComponentsBuilder uriComponentsBuilder
    ) {
        User user = userService.create(userRequest);
        URI location = uriComponentsBuilder
                .path(BASE_URL + "/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).body(userMapper.toResponseDTO(user));
    }
}
