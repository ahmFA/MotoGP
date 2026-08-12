package api.ahm.motogp.championship.infrastructure.adapter.in.rest;

public record ChampionshipRiderResponse(
        int id,
        int riderId,
        String riderName,
        int teamId,
        String teamName,
        int number
) {
}
