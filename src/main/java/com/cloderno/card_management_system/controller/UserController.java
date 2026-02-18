package com.cloderno.card_management_system.controller;

import com.cloderno.card_management_system.dto.UserRequestDTO;
import com.cloderno.card_management_system.dto.UserResponseDTO;
import com.cloderno.card_management_system.entity.User;
import com.cloderno.card_management_system.repository.UserRepository;
import com.cloderno.card_management_system.util.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    private static final String BASE_URL = "/api/users";

    @GetMapping
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(
        @Valid @RequestBody UserRequestDTO userRequest,
        UriComponentsBuilder uriComponentsBuilder
    ) {
        User mappedUser = userMapper.toEntity(userRequest);
        String hashedPassword = passwordEncoder.encode(userRequest.getPassword());
        mappedUser.setPasswordHash(hashedPassword);

        User userEntity = userRepository.save(mappedUser);

        URI location = uriComponentsBuilder
                .path(BASE_URL + "/{id}")
                .buildAndExpand(userEntity.getId())
                .toUri();

        return ResponseEntity.created(location).body(userMapper.toResponseDTO(userEntity));
    }
}
