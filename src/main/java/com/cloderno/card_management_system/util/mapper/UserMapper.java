package com.cloderno.card_management_system.util.mapper;

import com.cloderno.card_management_system.dto.UserRequestDTO;
import com.cloderno.card_management_system.dto.UserResponseDTO;
import com.cloderno.card_management_system.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserRequestDTO toRequestDTO(User user);
    User toEntity(UserRequestDTO userRequestDTO);
    UserResponseDTO toResponseDTO(User user);
}
