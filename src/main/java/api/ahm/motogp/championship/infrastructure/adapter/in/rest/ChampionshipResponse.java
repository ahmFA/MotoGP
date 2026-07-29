package api.ahm.motogp.championship.infrastructure.adapter.in.rest;

public record ChampionshipResponse(
        Integer id,
        Integer categoryId,
        String categoryName,
        Integer year
) {
}
