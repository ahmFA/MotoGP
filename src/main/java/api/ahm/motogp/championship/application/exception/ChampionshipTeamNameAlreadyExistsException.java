package api.ahm.motogp.championship.application.exception;

public class ChampionshipTeamNameAlreadyExistsException extends RuntimeException {
    public ChampionshipTeamNameAlreadyExistsException(String name) {
        super("Name " + name + " already exists in this championship");
    }
}
