package com.cloderno.card_management_system.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDTO {
    @NotBlank
    @Size(min=3, max=50)
    private String firstName;

    @NotBlank
    @Size(min=3, max=50)
    private String lastName;

    @NotBlank
    @Pattern(
        regexp = "^[\\w-.]+@[\\w-]+\\.[a-z]{2,}$",
        message = "must be a valid email"
    )
    private String email;

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;
}
