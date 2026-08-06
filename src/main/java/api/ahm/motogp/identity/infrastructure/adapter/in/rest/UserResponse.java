package api.ahm.motogp.identity.infrastructure.adapter.in.rest;

public record UserResponse(
        int id,
        String username,
        String email,
        String role
) {
}
