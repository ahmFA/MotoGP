package api.ahm.motogp.identity.infrastructure.adapter.in.rest;

public record LoginResponse(
        String accessToken,
        String tokenType,
        long expiresIn
) {
}
