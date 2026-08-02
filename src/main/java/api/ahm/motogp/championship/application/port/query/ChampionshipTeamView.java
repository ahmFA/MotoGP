package api.ahm.motogp.championship.application.port.query;

public record ChampionshipTeamView(
        int id,
        int teamId,
        String name,
        int constructorId,
        String constructorName
) {
}
