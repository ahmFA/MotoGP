package api.ahm.motogp.league.domain.model;

public record League(
        long id,
        int championshipId,
        String name,
        Boolean active
) {
    public League {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }
        if (championshipId <= 0) {
            throw new IllegalArgumentException("Championship ID must be positive");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
    }
}
