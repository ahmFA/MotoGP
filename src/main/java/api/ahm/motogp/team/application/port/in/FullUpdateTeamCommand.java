package api.ahm.motogp.team.application.port.in;

public record FullUpdateTeamCommand(
        Integer id,
        String name,
        Boolean active
) {
}
