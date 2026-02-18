package com.cloderno.card_management_system.service;

import com.cloderno.card_management_system.dto.UserRequestDTO;
import com.cloderno.card_management_system.entity.User;
import org.springframework.stereotype.Service;

public interface UserService {
    User create(UserRequestDTO userRequestDTO);
}
