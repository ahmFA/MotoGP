package api.ahm.motogp.league.application.exception;

public class LeagueNotFoundException extends RuntimeException {
    public LeagueNotFoundException(long leagueId) {
        super("League with id " + leagueId + " was not found");
    }
}
