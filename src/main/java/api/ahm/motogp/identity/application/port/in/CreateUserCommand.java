package api.ahm.motogp.identity.application.port.in;

public record CreateUserCommand(
        String username,
        String email,
        String password,
        String role
) {
}
