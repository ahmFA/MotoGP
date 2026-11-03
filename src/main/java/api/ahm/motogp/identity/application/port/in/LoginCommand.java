package api.ahm.motogp.identity.application.port.in;

public record LoginCommand(
        String username,
        String password
) {
}
