package api.ahm.motogp.championship.application.exception;

public class ChampionshipNotFoundException extends RuntimeException {
    public ChampionshipNotFoundException(Integer id) {
        super("Championship with id " + id + " was not found");
    }
}
