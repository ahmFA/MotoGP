package api.ahm.motogp.championship.application.exception;

public class ChampionshipAlreadyExistsException extends RuntimeException {
    public ChampionshipAlreadyExistsException(Integer categoryId, Integer year) {
        super("Championship with category ID " + categoryId + " and year " + year + " already exists");
    }
}
