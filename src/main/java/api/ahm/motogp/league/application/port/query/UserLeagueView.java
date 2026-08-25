package api.ahm.motogp.league.application.port.query;

public record UserLeagueView(
        long id,
        long leagueId,
        long userId,
        String username,
        String email,
        String role
) {
}
