package api.ahm.motogp.championship.application.exception;

public class ChampionshipGrandPrixNotFoundException extends RuntimeException {
    public ChampionshipGrandPrixNotFoundException(int id) {
        super("Championship grand prix with ID: " + id + " not found");
    }
}
