package api.ahm.motogp.league.application.port.in.command;

public record CreateLeagueCommand(
        int championshipId,
        String name
) {
}
