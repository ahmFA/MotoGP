package api.ahm.motogp.league.infrastructure.adapter.in.rest;

public record LeagueResponse(
        long id,
        int championshipId,
        String name
) {
}
