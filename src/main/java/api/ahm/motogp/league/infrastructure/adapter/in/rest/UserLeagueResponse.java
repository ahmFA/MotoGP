package api.ahm.motogp.league.infrastructure.adapter.in.rest;

public record UserLeagueResponse(
        long id,
        long leagueId,
        long userId,
        String username,
        String email,
        String role
) {
}
