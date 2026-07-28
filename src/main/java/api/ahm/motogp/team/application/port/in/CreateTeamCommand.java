package api.ahm.motogp.team.application.port.in;

public record CreateTeamCommand(
        String name,
        Boolean active
) {
}
