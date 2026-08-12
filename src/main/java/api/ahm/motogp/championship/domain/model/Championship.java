package api.ahm.motogp.championship.domain.model;

public record Championship(
        int id,
        int categoryId,
        String categoryName,
        int year
) {
    public Championship {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }
        if (categoryId <= 0) {
            throw new IllegalArgumentException("Category ID must be positive");
        }
        if (categoryName == null || categoryName.isBlank()) {
            throw new IllegalArgumentException("Category name cannot be null or blank");
        }
        if (year <= 0) {
            throw new IllegalArgumentException("Year must be positive");
        }
    }
}
