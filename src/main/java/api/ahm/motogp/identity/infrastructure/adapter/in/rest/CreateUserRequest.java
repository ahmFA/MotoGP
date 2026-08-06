package api.ahm.motogp.identity.infrastructure.adapter.in.rest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
        @NotBlank
        String username,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password,
        String role
) {
}
