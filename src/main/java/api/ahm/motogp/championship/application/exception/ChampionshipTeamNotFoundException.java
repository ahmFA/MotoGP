package api.ahm.motogp.championship.application.exception;

public class ChampionshipTeamNotFoundException extends RuntimeException {
    public ChampionshipTeamNotFoundException(int id) {
        super("Championship team with ID: " + id + " not found");
    }
}
