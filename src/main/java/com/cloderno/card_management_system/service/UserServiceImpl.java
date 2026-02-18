package com.cloderno.card_management_system.service;

import com.cloderno.card_management_system.dto.UserRequestDTO;
import com.cloderno.card_management_system.entity.User;
import com.cloderno.card_management_system.repository.UserRepository;
import com.cloderno.card_management_system.util.mapper.UserMapper;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User create(UserRequestDTO userRequestDTO) {
        User user = userMapper.toEntity(userRequestDTO);
        user.setPasswordHash(passwordEncoder.encode(userRequestDTO.getPassword()));

        return userRepository.save(user);
    }
}
