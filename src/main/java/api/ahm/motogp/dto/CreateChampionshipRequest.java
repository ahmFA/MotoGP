package api.ahm.motogp.dto;

public record CreateChampionshipRequest(
        Integer categoryId,
        Integer year
) {
}
