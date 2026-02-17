package com.cloderno.card_management_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserResponseDTO {
    @NotBlank
    @Size(min=3, max=50)
    private String firstName;

    @NotBlank
    @Size(min=3, max=50)
    private String lastName;

    @NotBlank
    @Email
    private String email;
}
