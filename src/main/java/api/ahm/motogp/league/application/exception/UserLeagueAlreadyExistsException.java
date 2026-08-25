package api.ahm.motogp.league.application.exception;

public class UserLeagueAlreadyExistsException extends RuntimeException {
    public UserLeagueAlreadyExistsException(long leagueId, long userId) {
        super("User with id " + userId + " is already registered in league " + leagueId);
    }
}
