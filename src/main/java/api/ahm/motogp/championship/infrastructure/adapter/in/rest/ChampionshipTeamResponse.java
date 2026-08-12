package api.ahm.motogp.championship.infrastructure.adapter.in.rest;

public record ChampionshipTeamResponse(
        int id,
        int teamId,
        String teamName,
        int constructorId,
        String constructorName
) {
}
