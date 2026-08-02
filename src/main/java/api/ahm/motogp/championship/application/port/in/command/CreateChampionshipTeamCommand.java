package api.ahm.motogp.championship.application.port.in.command;

public record CreateChampionshipTeamCommand(
        int teamId,
        int constructorId,
        int championshipId,
        String name

) {
}
