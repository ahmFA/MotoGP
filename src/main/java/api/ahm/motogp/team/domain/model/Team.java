package api.ahm.motogp.team.domain.model;

public record Team(
        int id,
        String name,
        boolean active
) {
    public Team {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
    }
}
