package api.ahm.motogp.league.application.exception;

public class UserLeagueNotFoundException extends RuntimeException {
    public UserLeagueNotFoundException(long leagueId, long userId) {
        super("User with id " + userId + " was not found in league " + leagueId);
    }
}
