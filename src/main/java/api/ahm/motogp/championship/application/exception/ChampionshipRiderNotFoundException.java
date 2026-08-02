package api.ahm.motogp.championship.application.exception;

public class ChampionshipRiderNotFoundException extends RuntimeException {
    public ChampionshipRiderNotFoundException(int id) {
        super("Championship rider with ID: " + id + " not found");
    }
}
