package api.ahm.motogp.grandprix.domain.model;

public record GrandPrix(
        int id,
        String name,
        String circuitName,
        int countryId,
        String countryName
) {
    public GrandPrix {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (circuitName == null || circuitName.isBlank()) {
            throw new IllegalArgumentException("Circuit name cannot be null or blank");
        }
        if (countryId <= 0) {
            throw new IllegalArgumentException("Country ID must be positive");
        }
        if (countryName == null || countryName.isBlank()) {
            throw new IllegalArgumentException("Country name cannot be null or blank");
        }
    }
}
