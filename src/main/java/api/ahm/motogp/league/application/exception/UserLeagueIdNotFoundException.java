package api.ahm.motogp.league.application.exception;

public class UserLeagueIdNotFoundException extends RuntimeException {
    public UserLeagueIdNotFoundException(Long userLeagueId) {
        super("User league with id " + userLeagueId + " not found");
    }
}
