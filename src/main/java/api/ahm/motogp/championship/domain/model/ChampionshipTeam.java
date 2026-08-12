package api.ahm.motogp.championship.domain.model;

public record ChampionshipTeam(
        int championshipTeamId,
        int teamId,
        int constructorId,
        int championshipId,
        String name

) {
}
